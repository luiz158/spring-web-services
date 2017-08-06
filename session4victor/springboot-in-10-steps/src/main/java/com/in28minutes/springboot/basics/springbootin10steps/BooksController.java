package com.in28minutes.springboot.basics.springbootin10steps;

import java.util.LinkedList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BooksController {

	@GetMapping("/books")
	public List<Book> getAllBoooks() {

		return getBooksList();
	}

	private List<Book> getBooksList() {
		List<Book> books = new LinkedList<>();

		books.add(new Book(1L, "Learning Scala", "Martin Odersky"));
		books.add(new Book(2L, "The C programming Language", "B. Kernighan"));

		return books;
	}

}
