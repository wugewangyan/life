package com.napoleon.life.core.entity;

import java.io.File;
import java.io.IOException;

import org.apache.avro.Schema;
import org.apache.avro.specific.SpecificRecord;
import org.springframework.core.io.ClassPathResource;

import com.napoleon.life.exception.CommonException;

public class Book implements SpecificRecord {

	public static Schema SCHEMA$ = null;
			//.parse("{\"type\":\"record\",\"name\":\"Book\",\"namespace\":\"com.napoleon.life.core.entity\",\"fields\":[{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"id\",\"type\":[\"int\",\"null\"]},{\"name\":\"category\",\"type\":[\"string\",\"null\"]}]}");

	public CharSequence name;
	
	public Integer id;
	
	public CharSequence category;

	public Book() {
		if(SCHEMA$ == null){
			try {
				SCHEMA$ = new Schema.Parser().parse(new File("/Users/wuge/projects/business_system/life/life-core/src/main/avro/book.avsc"));
			} catch (IOException e) {
				throw new CommonException(e);
			}
		}
	}

	// Used by DatumWriter. Applications should not call.
	@Override
	public Object get(int field$) {
		switch (field$) {
		case 0:
			return name;
		case 1:
			return id;
		case 2:
			return category;
		default:
			throw new org.apache.avro.AvroRuntimeException("Bad index");
		}
	}

	// Used by DatumReader. Applications should not call.
	@Override
	public void put(int field$, java.lang.Object value$) {
		switch (field$) {
		case 0:
			name = (CharSequence)value$;
			break;
		case 1:
			id = (Integer)value$;
			break;
		case 2:
			category = (CharSequence)value$;
			break;
		default:
			throw new org.apache.avro.AvroRuntimeException("Bad index");
		}
	}

	public CharSequence getName() {
		return name;
	}

	public void setName(CharSequence name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CharSequence getCategory() {
		return category;
	}

	public void setCategory(CharSequence category) {
		this.category = category;
	}

	@Override
	public Schema getSchema() {
		return SCHEMA$;
	}
}
