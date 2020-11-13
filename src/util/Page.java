package util;
 
public class Page {
     private int totalPage;
     private int currentPage;
     private int totalRecord;
     private int currentRecord;
     private int pageSize=8;
     //현재페이�? 얻고 설정하기
     public int getCurrentPage(){
    	 return currentPage;
     }
     public void setCurrentPage(int currentRecord,int pageSize){
    	 if(currentRecord%pageSize==0){
    		 currentPage=currentRecord/pageSize;
    	 }
    	 else{
    		 currentPage=currentRecord/pageSize+1;
    	 }
     }
     //현재기록 얻고 설정하기
     public int getCurrentRecord(){
    	 return currentRecord;
     }
     public void setCurrentRecord(int currentRecord){
    	 this.currentRecord=currentRecord;
     }
     //페이�?마다�? 수량 얻고 설정하기
     public int getPageSize(){
    	 return pageSize;
     }
     public void setPageSize(int pageSize){
    	 this.pageSize=pageSize;
     }
     //총페이지 얻고 설정하기
     public int getTotalPage(){
    	 return totalPage;
     }
     public void setTotalPage(int totalRecord,int pageSize){
    	 if(totalRecord%pageSize==0){
    		 totalPage=totalRecord/pageSize;
    	 }
    	 else{
    		 totalPage=totalRecord/pageSize+1;
    	 }
     }
     //�? 기록 얻고 설정
     public int getTotalRecord(){
    	 return totalRecord;
     }
     public void setTotalRecord(int totalRecord){
    	 this.totalRecord=totalRecord;
     }
 
     
}
