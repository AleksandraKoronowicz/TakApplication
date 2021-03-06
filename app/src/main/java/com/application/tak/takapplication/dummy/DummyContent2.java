package com.application.tak.takapplication.dummy;

import com.application.tak.takapplication.data_access.GetCategories;
import com.application.tak.takapplication.data_model.Category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent2 {


    public DummyContent2()
    {
    }

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Category> ITEMS = new ArrayList<Category>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<Integer, Category> ITEM_MAP = new HashMap<Integer, Category>();

    //private static final int COUNT = 25;

    static {
        // Add some sample items.
        //  for (int i = 1; i <= COUNT; i++) {
        // addItem(createDummyItem(i));
       /* addItem(new DummyItem("1", "Wyrzuć smieci", "2 lipca 2015", "200 metrów od Twojej szkoły", "509 333 333"));
        addItem(new DummyItem("1", "Wyrzuć smieci", "2 lipca 2015", "200 metrów od Twojej szkoły", "509 333 333"));
        addItem(new DummyItem("1", "Wyrzuć smieci", "2 lipca 2015", "200 metrów od Twojej szkoły", "509 333 333"));
        addItem(new DummyItem("1", "Wyrzuć smieci", "2 lipca 2015", "200 metrów od Twojej szkoły", "509 333 333"));
        addItem(new DummyItem("1", "Wyrzuć smieci", "2 lipca 2015", "200 metrów od Twojej szkoły", "509 333 333"));
        addItem(new DummyItem("1", "Wyrzuć smieci", "2 lipca 2015", "200 metrów od Twojej szkoły", "509 333 333"));
        addItem(new DummyItem("1", "Wyrzuć smieci", "2 lipca 2015", "200 metrów od Twojej szkoły", "509 333 333"));
        addItem(new DummyItem("1", "Wyrzuć smieci", "2 lipca 2015", "200 metrów od Twojej szkoły", "509 333 333"));
        addItem(new DummyItem("1", "Wyrzuć smieci", "2 lipca 2015", "200 metrów od Twojej szkoły", "509 333 333"));
        addItem(new DummyItem("1", "Wyrzuć smieci", "2 lipca 2015", "200 metrów od Twojej szkoły", "509 333 333"));
        addItem(new DummyItem("1", "Wyrzuć smieci", "2 lipca 2015", "200 metrów od Twojej szkoły", "509 333 333"));
        addItem(new DummyItem("1", "Wyrzuć smieci", "2 lipca 2015", "200 metrów od Twojej szkoły", "509 333 333"));
*/
    }


    private static void addItem(Category item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.get_Id(), item);
    }

    private static Category createDummyItem(int position) {
        return new Category();
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }
}
    /**
     * A dummy item representing a piece of content.
     */
   /*public static class DummyItem {
        public final String id;
        public final String task;
        public final String dateTime;
        public final String place;
        public final String phoneNumber;

       public DummyItem(String id, String task, String dateTime, String place, String phoneNumber) {
            this.id = id;
            this.task = task;
            this.dateTime = dateTime;
            this.place = place;
            this.phoneNumber=phoneNumber;
        }

        @Override
        public String toString() {
            return task;
        }
    }*/
