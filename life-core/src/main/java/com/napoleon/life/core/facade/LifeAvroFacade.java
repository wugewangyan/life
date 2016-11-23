package com.napoleon.life.core.facade;  

import com.napoleon.life.core.entity.Book;
   
  
public interface LifeAvroFacade{

	public String avroSerialize(Book book);

	public String avroDeserialize(String path);

}
 
