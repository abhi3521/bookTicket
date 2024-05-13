package com.example.bookTicket.service;

import com.example.bookTicket.DTO.MovieShow;
import com.example.bookTicket.DTO.MovieShowInterface;
import com.example.bookTicket.entity.Hall;
import com.example.bookTicket.entity.Seat;
import com.example.bookTicket.entity.Show;
import com.example.bookTicket.repository.HallRepository;
import com.example.bookTicket.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.example.bookTicket.Util.formatDateTime;
@Service
public class ShowServiceImpl implements ShowService {
    @Autowired
    ShowRepository showRepository;
    @Autowired
    HallRepository hallRepository;
    @Override
    public ResponseEntity<String> addShow(Show show) {
        Date startTime = formatDateTime(show.getShowStartTime());
        Date endTime = formatDateTime(show.getShowEndTime());
        Long hallId = show.getHallId();
        List<Show> allShow = showRepository.findAll();
        boolean canBeAdded = allShow.stream()
                .filter(s -> s.getHallId() == hallId)
                .noneMatch(s ->
                        (startTime.equals(formatDateTime(s.getShowStartTime())) || startTime.after(formatDateTime(s.getShowStartTime())) && startTime.before(formatDateTime(s.getShowEndTime()))) ||
                                (endTime.after(formatDateTime(s.getShowStartTime())) && endTime.before(formatDateTime(s.getShowEndTime())) || endTime.equals(formatDateTime(s.getShowEndTime()))) ||
                                ((startTime.before(formatDateTime(s.getShowStartTime())) || startTime.equals(formatDateTime(s.getShowStartTime())))
                                        && (endTime.equals(formatDateTime(s.getShowEndTime())) || endTime.after(formatDateTime(s.getShowEndTime())))));

        if(canBeAdded) {
            Optional<Hall> hall = hallRepository.findById(show.getHallId());
            if(hall.isPresent()) {
                List<Seat> seatsToAdd = IntStream.rangeClosed(1, hall.get().getNoOfSeats())
                        .mapToObj(seatNo -> {
                            Seat seat = new Seat();
                            seat.setSeatNo(seatNo);
                            seat.setShow(show);
                            return seat;
                        })
                        .collect(Collectors.toList());

                show.setSeats(seatsToAdd);
            }
            showRepository.save(show);
            return ResponseEntity.ok("Show added with start time : " + show.getShowStartTime());
        }
        throw new RuntimeException("There is already one show in the same hall on same time!");
    }
    @Override
    public ResponseEntity<List<MovieShowInterface>> getMoviesWithShowTime() {
        LocalDateTime currTime = LocalDateTime.now();
        List<MovieShowInterface> movieShowInterfaceList = showRepository.findUpcomingShowsWithMovieDetails(currTime);

        if(movieShowInterfaceList != null && movieShowInterfaceList.size() != 0) {
            return ResponseEntity.ok(movieShowInterfaceList);
        }
        throw new RuntimeException("No show available");
    }



    @Override
    public ResponseEntity<List<MovieShow>> getShowByMovieTitle(String title) {
        LocalDateTime currTime = LocalDateTime.now();
        List<MovieShow> movieShowList = showRepository.findUpcomingShowsByMovieTitle(currTime, title);
        if(movieShowList != null && movieShowList.size() != 0) {
            return ResponseEntity.ok(movieShowList);
        }
        throw new RuntimeException("No show available");
    }
}
