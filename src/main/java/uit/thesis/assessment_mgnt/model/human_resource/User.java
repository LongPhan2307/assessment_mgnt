package uit.thesis.assessment_mgnt.model.human_resource;

import javax.persistence.*;

@Entity
@Table(name = "assessment_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @Version
    protected int version;
}
