package io.github.cepr0.demo;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

@RestController
@SpringBootApplication
public class Application {

    private final UserRepo userRepo;
    private final ActionRepo actionRepo;
    private final ContactRepo contactRepo;

    public Application(UserRepo userRepo, ActionRepo actionRepo, ContactRepo contactRepo) {
        this.userRepo = userRepo;
        this.actionRepo = actionRepo;
        this.contactRepo = contactRepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping("/report")
    public Collection<UserDto> report() {
        return userRepo.getReport();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void populateData() {
        List<User> users = userRepo.saveAll(List.of(
                new User().setName("John"),
                new User().setName("Jane"),
                new User().setName("Joe")
        ));

        actionRepo.saveAll(List.of(
                new Action().setName("action1").setType(ActionType.ONE).setUser(users.get(0)),
                new Action().setName("action2").setType(ActionType.TWO).setUser(users.get(0)),
                new Action().setName("action3").setType(ActionType.TWO).setUser(users.get(0)),
                new Action().setName("action4").setType(ActionType.THREE).setUser(users.get(0)),
                new Action().setName("action5").setType(ActionType.THREE).setUser(users.get(0)),
                new Action().setName("action6").setType(ActionType.THREE).setUser(users.get(0)),
                new Action().setName("action7").setType(ActionType.ONE).setUser(users.get(1)),
                new Action().setName("action8").setType(ActionType.TWO).setUser(users.get(1)),
                new Action().setName("action9").setType(ActionType.TWO).setUser(users.get(1)),
                new Action().setName("action12").setType(ActionType.ONE).setUser(users.get(2))
        ));

        contactRepo.saveAll(List.of(
           new Contact().setName("contact1").setUser(users.get(0)),
           new Contact().setName("contact2").setUser(users.get(0)),
           new Contact().setName("contact3").setUser(users.get(0)),
           new Contact().setName("contact4").setUser(users.get(1)),
           new Contact().setName("contact5").setUser(users.get(1)),
           new Contact().setName("contact6").setUser(users.get(2))
        ));
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2Server() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
    }
}
