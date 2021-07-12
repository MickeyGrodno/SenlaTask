package eu.senla.shabalin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ResponseData {
    private Integer page;
    private Integer perPage;
    private Integer total;
    private Integer totalPages;
    private List<User> data;
}
