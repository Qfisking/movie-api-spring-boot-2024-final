package nxu.it.movieapi.service.pg;

public class PageUtil{
    public static int getTotalPage(int totalCount,int pageSize){
        if(totalCount%pageSize==0){
            return totalCount/pageSize;
        }else{
            return totalCount/pageSize +1;
        }
    }

    public static int getOffset(int pageNumber,int pageSize){
        return (pageNumber - 1)*pageSize;
    }

}
