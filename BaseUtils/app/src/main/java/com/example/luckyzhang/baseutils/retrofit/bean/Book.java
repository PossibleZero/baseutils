package com.example.luckyzhang.baseutils.retrofit.bean;

import java.util.List;

/**
 * Created by luckyzhang on 2017/5/8.
 */

public class Book {


    /**
     * max : 10
     * numRaters : 11215
     * average : 9.2
     * min : 0
     */

    private RatingBean rating;
    /**
     * rating : {"max":10,"numRaters":11215,"average":"9.2","min":0}
     * subtitle :
     * author : ["（法）圣埃克苏佩里"]
     * pubdate : 2000-9-1
     * tags : [{"count":2909,"name":"小王子","title":"小王子"},{"count":2305,"name":"童话","title":"童话"},{"count":1425,"name":"圣埃克苏佩里","title":"圣埃克苏佩里"},{"count":1074,"name":"法国","title":"法国"},{"count":849,"name":"经典","title":"经典"},{"count":738,"name":"外国文学","title":"外国文学"},{"count":616,"name":"感动","title":"感动"},{"count":465,"name":"寓言","title":"寓言"}]
     * origin_title :
     * image : https://img3.doubanio.com/mpic/s3294754.jpg
     * binding : 平装
     * translator : ["胡雨苏"]
     * catalog : 序言：法兰西玫瑰 小王子 圣埃克苏佩里年表
     * pages : 111
     * images : {"small":"https://img3.doubanio.com/spic/s3294754.jpg","large":"https://img3.doubanio.com/lpic/s3294754.jpg","medium":"https://img3.doubanio.com/mpic/s3294754.jpg"}
     * alt : https://book.douban.com/subject/1003078/
     * id : 1003078
     * publisher : 中国友谊出版公司
     * isbn10 : 7505715666
     * isbn13 : 9787505715660
     * title : 小王子
     * url : https://api.douban.com/v2/book/1003078
     * alt_title :
     * author_intro : 2
     * summary : 1
     * price : 19.8
     */

    private String subtitle;
    private String pubdate;
    private String origin_title;
    private String image;
    private String binding;
    private String catalog;
    private String pages;
    /**
     * small : https://img3.doubanio.com/spic/s3294754.jpg
     * large : https://img3.doubanio.com/lpic/s3294754.jpg
     * medium : https://img3.doubanio.com/mpic/s3294754.jpg
     */

    private ImagesBean images;
    private String alt;
    private String id;
    private String publisher;
    private String isbn10;
    private String isbn13;
    private String title;
    private String url;
    private String alt_title;
    private String author_intro;
    private String summary;
    private String price;
    private List<String> author;
    /**
     * count : 2909
     * name : 小王子
     * title : 小王子
     */

    private List<TagsBean> tags;
    private List<String> translator;

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getOrigin_title() {
        return origin_title;
    }

    public void setOrigin_title(String origin_title) {
        this.origin_title = origin_title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlt_title() {
        return alt_title;
    }

    public void setAlt_title(String alt_title) {
        this.alt_title = alt_title;
    }

    public String getAuthor_intro() {
        return author_intro;
    }

    public void setAuthor_intro(String author_intro) {
        this.author_intro = author_intro;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public List<TagsBean> getTags() {
        return tags;
    }

    public void setTags(List<TagsBean> tags) {
        this.tags = tags;
    }

    public List<String> getTranslator() {
        return translator;
    }

    public void setTranslator(List<String> translator) {
        this.translator = translator;
    }

    public static class RatingBean {
        private int max;
        private int numRaters;
        private String average;
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public int getNumRaters() {
            return numRaters;
        }

        public void setNumRaters(int numRaters) {
            this.numRaters = numRaters;
        }

        public String getAverage() {
            return average;
        }

        public void setAverage(String average) {
            this.average = average;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }
    }

    public static class ImagesBean {
        private String small;
        private String large;
        private String medium;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }
    }

    public static class TagsBean {
        private int count;
        private String name;
        private String title;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "TagsBean{" +
                    "count=" + count +
                    ", name='" + name + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }
    }
}
