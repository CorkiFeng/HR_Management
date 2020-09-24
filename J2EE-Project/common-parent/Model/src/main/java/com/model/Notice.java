package com.model;

public class Notice {
    private Integer id;

    private String usename;

    private String name;

    private String title;

    private String remark;

    private String content;

    public Notice() {
    }

    public Notice(Integer id, String usename, String name, String title, String remark, String content) {
        this.id = id;
        this.usename = usename;
        this.name = name;
        this.title = title;
        this.remark = remark;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsename() {
        return usename;
    }

    public void setUsename(String usename) {
        this.usename = usename == null ? null : usename.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Override
    public String toString() {
        return "Notice{" +
                "id=" + id +
                ", usename='" + usename + '\'' +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", remark='" + remark + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}