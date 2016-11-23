package com.napoleon.life.core.facade.impl;  
   
 import java.io.File;
import java.io.IOException;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.napoleon.life.core.entity.Book;
import com.napoleon.life.core.facade.LifeAvroFacade;
import com.napoleon.life.exception.CommonResultCode;
import com.napoleon.life.framework.result.CommonRltUtil;
 
 @Service
 public class LifeAvroFacadeImpl implements LifeAvroFacade{

	private static final Logger logger = LoggerFactory.getLogger(LifeWaistFacadeImpl.class);
		
	@Override
	public String avroSerialize(Book book) {
		DataFileWriter<Book> bookFileWriter = null;
		try {
			File store = File.createTempFile("book", ".avro");
			logger.info("serializing books to temp file: " + store.getPath());
			DatumWriter<Book> bookDatumWriter = new SpecificDatumWriter<Book>(Book.class);
			bookFileWriter = new DataFileWriter<Book>(bookDatumWriter);
			bookFileWriter.create(book.getSchema(), store);
			bookFileWriter.append(book);
			return CommonRltUtil.createCommonRltToString(CommonResultCode.SUCCESS);
		} catch (IOException e) {
			return CommonRltUtil.createCommonRltToString(CommonResultCode.SYSTEM_ERR, e);
		}finally{
			if(bookFileWriter != null){
				try {
					bookFileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	@Override
	public String avroDeserialize(String path) {
		DataFileReader<Book> bookFileReader = null;
		try {
			File store = new File(path);
			logger.info("deserialize books from temp file: " + store.getPath());
			DatumReader<Book> bookDatumReader = new SpecificDatumReader<Book>(Book.class);
			bookFileReader = new DataFileReader<Book>(store, bookDatumReader);
			
			Book book = null;
			while(bookFileReader.hasNext()){
				book = bookFileReader.next();
			}
			return CommonRltUtil.createCommonRltToString(CommonResultCode.SUCCESS, book.toString());
		} catch (IOException e) {
			return CommonRltUtil.createCommonRltToString(CommonResultCode.SYSTEM_ERR, e);
		}finally{
			if(bookFileReader != null){
				try {
					bookFileReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
 
