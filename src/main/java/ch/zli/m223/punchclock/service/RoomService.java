package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.Room;
import ch.zli.m223.punchclock.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    private RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room createRoom(Room room) {
        return roomRepository.saveAndFlush(room);
    }

    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    public Room findRoom(Long id) { return roomRepository.getOne(id);}

    public void deleteRoom(Long id){
        roomRepository.deleteById(id);
    }

    public Room updateRoom(Room room){
        return roomRepository.save(room);
    }

}
