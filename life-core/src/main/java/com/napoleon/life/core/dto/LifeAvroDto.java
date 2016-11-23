package com.napoleon.life.core.dto;  
   
 import com.napoleon.life.framework.base.BaseDto;
 
 public class LifeAvroDto extends BaseDto{  
   
    private Integer id;
    
    private String category;
    
    private String name;
    
    private String bookPath;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBookPath() {
		return bookPath;
	}

	public void setBookPath(String bookPath) {
		this.bookPath = bookPath;
	}
 }  