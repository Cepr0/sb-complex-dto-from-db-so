package io.github.cepr0.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*;

public interface UserRepo extends JpaRepository<User, Long> {

    default Collection<UserDto> getReport() {
        Map<Long, UserDto> result = new HashMap<>();

        getUserPlainDtos().forEach(dto -> {
            long userId = dto.getUserId();
            long actionCount = dto.getActionCount();

            UserDto userDto = result.getOrDefault(userId, new UserDto());
            userDto.setUserId(userId);
            userDto.setContactCount(dto.getContactCount());
            userDto.getActions().compute(dto.getActionType(), (type, count) -> count != null ? count + actionCount : actionCount);
            result.put(userId, userDto);
        });

        return result.values();
    }

    @Query("select new io.github.cepr0.demo.UserPlainDto( " +
            "  a.user.id, " +
            "  count(distinct c.id), " +
            "  a.type, " +
            "  count(distinct a.id) " +
            ") " +
            "from " +
            "  Action a " +
            "  join Contact c on c.user.id = a.user.id " +
            "group by " +
            "  a.user.id, a.type")
    List<UserPlainDto> getUserPlainDtos();
}
