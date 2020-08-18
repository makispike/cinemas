package ephec.integration.cinemas.persistence.control;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;

@Getter
@Setter
public class VersionDTO {
    @Id
    private Integer versionId;
    private String versionLabel;
}
