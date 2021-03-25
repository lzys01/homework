package com.bytedance.practice5.model;

import java.util.List;

public class SocketBean {


    /**
     * feeds : [{"_id":"605befbdbdd4f300477178ec","student_id":"517021910966","from":"\"林志予\"","to":"\"111\"","content":"\"1111\"","extra_value":"","image_url":"https://sf1-hscdn-tos.pstatp.com/obj/developer-baas/baas/tt41nq/1e6a9eca5db3ad26_1616637885639.jpg","image_w":1024,"image_h":768,"createdAt":"2021-03-25T02:04:45.699Z","updatedAt":"2021-03-25T02:04:45.699Z"},{"_id":"605b444f180323003e623169","student_id":"517021910966","from":"\"林志予\"","to":"\"111\"","content":"\"111\"","extra_value":"","image_url":"https://sf1-hscdn-tos.pstatp.com/obj/developer-baas/baas/tt41nq/8d84760fe7d443e1_1616593999832.jpg","image_w":1024,"image_h":768,"createdAt":"2021-03-24T13:53:19.883Z","updatedAt":"2021-03-24T13:53:19.883Z"},{"_id":"605b3b3c180323003e62311d","student_id":"517021910966","from":"\"林志予\"","to":"\"qqq\"","content":"\"qqq\"","extra_value":"","image_url":"https://lf6-hscdn-tos.pstatp.com/obj/developer-baas/baas/tt41nq/514e5d44f2e9235d_1616591676739.jpg","image_w":1024,"image_h":792,"createdAt":"2021-03-24T13:14:36.802Z","updatedAt":"2021-03-24T13:14:36.803Z"},{"_id":"605b3b0c180323003e623116","student_id":"517021910966","from":"\"林志予\"","to":"\"211\"","content":"\"qqq\"","extra_value":"","image_url":"https://lf1-hscdn-tos.pstatp.com/obj/developer-baas/baas/tt41nq/6f12914809f647c8_1616591628640.jpg","image_w":500,"image_h":500,"createdAt":"2021-03-24T13:13:48.695Z","updatedAt":"2021-03-24T13:13:48.695Z"},{"_id":"605b3ad2180323003e623111","student_id":"517021910966","from":"\"林志予\"","to":"\"111\"","content":"\"111\"","extra_value":"","image_url":"https://sf6-hscdn-tos.pstatp.com/obj/developer-baas/baas/tt41nq/ac5172d323472b4e_1616591570252.jpg","image_w":300,"image_h":300,"createdAt":"2021-03-24T13:12:50.312Z","updatedAt":"2021-03-24T13:12:50.312Z"}]
     * success : true
     */

    private List<Message> feeds;
    private Boolean success;

    public List<Message> getFeeds() {
        return feeds;
    }

    public void setFeeds(List<Message> feeds) {
        this.feeds = feeds;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

}
