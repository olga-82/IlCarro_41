package models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString

public class UserLombok {
    String name;
    String lastName;
    String email;
    String password;
}
