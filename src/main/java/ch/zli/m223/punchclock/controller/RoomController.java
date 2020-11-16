package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.Room;
import ch.zli.m223.punchclock.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * The Room endpoint.
 * GET: {URL}/rooms - To see all rooms
 * DELETE: {URL}/rooms/{id} - To delete a room by the room-id
 * PUT: {URL}/rooms/{id} - To update a room by the room-id
 */
@RestController
@RequestMapping("/rooms")
public class RoomController {

    private RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Room> getAllRooms(){return roomService.findAll();}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Room createRoom(@Valid @RequestBody Room room) {
        return roomService.createRoom(room);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRoom(@PathVariable Long id){
        roomService.deleteRoom(id);
    }

    @PutMapping(value = "/{id}")
    public Room updateRoom(@RequestBody Room room, @PathVariable Long id){
        room.setId(id);
        return roomService.updateRoom(room);
    }
}
