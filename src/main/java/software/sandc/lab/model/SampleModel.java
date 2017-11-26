package software.sandc.lab.model;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;
import software.sandc.lab.view.AdminView;
import software.sandc.lab.view.UserView;

@Data
public class SampleModel {
    
    @JsonView(AdminView.class)
    private String id;

    @JsonView(UserView.class)
    private String username;
    
    private String version;

}
