package eu.senla.shabalin;

import eu.senla.shabalin.pageobjects.ComputerDatabasePage;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComputerDatabaseTest {
    private ComputerDatabasePage page = new ComputerDatabasePage();

    @Test
    public void computerNameSortByAscTest() {
        page.openFirstPage();
        page.sortAllBy(SortedColumn.NAME, SortBy.DESC);
        List<String> firstPageList = page.getAllComputerNameInString();

        page.openPenultimatePage();
        page.sortAllBy(SortedColumn.NAME, SortBy.DESC);
        List<String> penultimatePageList = page.getAllComputerNameInString();

        List<String> sortedFirstPageList = new ArrayList<>(firstPageList);
        List<String> sortedPenultimatePageList = new ArrayList<>(penultimatePageList);

        Collections.sort(sortedFirstPageList);
        Collections.sort(sortedPenultimatePageList);





//        page.sortAllByComputerNameAsc();
//        System.out.println();
//        Collections.sort(list, (o1, o2) -> (o1.getAgentName().compareTo(o2.getAgentName())));
    }
}
