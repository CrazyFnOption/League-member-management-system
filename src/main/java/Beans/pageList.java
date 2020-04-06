package Beans;

import java.util.List;

public class pageList<Member> {
    private int total;      // 当前数据量的总页数
    public int current;    // 当前处于的页数
    public int pages;      // 当前的总页数
    public int row;        // 每一页出多少数
    public List<Member>list;

    public pageList() {
    }

    public pageList(int total, int current, int pages, int row, List<Member> list) {
        this.total = total;
        this.current = current;
        this.pages = pages;
        this.row = row;
        this.list = list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public List<Member> getList() {
        return list;
    }

    @Override
    public String toString() {
        return "pageList{" +
                "total=" + total +
                ", current=" + current +
                ", pages=" + pages +
                ", row=" + row +
                ", list=" + list +
                '}';
    }

    public void setList(List<Member> list) {
        this.list = list;
    }
}
