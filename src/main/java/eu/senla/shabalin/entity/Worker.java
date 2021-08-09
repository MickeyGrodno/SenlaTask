package eu.senla.shabalin.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class Worker {
    @JsonProperty("name")
    private String name;
    @JsonProperty("job")
    private String job;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("createdAt")
    private String createdAt;
    @JsonProperty("updatedAt")
    private String updatedAt;

    public Worker(String name, String job) {
        this.name = name;
        this.job = job;
    }
    public String getWorkerInJsonString() {
        Map<String,String> workerMap = new HashMap<>();
        workerMap.put("name", this.name);
        workerMap.put("job", this.job);
        return new JSONObject(workerMap).toString();
    }

    @Override
    public String toString() {
        String workerInString = "Worker{" +
                "name='" + name + '\'' +
                ", job='" + job + '\'' +
                ", id=" + id +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
        System.out.println(workerInString);
        return workerInString;
    }
}
