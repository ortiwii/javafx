package ehu.isad;

import java.util.Arrays;

public class Details {
    public String[] publishers;
    public Integer number_of_pages;
    public String title;

    @Override
    public String toString() {
        return "Details{" +
                "publishers=" + Arrays.toString(publishers) +
                ", number_of_pages=" + number_of_pages +
                ", title='" + title + '\'' +
                '}';
    }
}
