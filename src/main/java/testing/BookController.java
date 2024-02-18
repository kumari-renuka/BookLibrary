package testing;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

@RequestMapping(path = "/geek")
public class BookController {

	@Autowired
	private BookRepository bookRepository;

	@PostMapping(path = "/addbook")
	public @ResponseBody String addBooks(@RequestParam String bookName, @RequestParam String isbnNumber) {

		
		Book book = new Book();
		book.setBookName(bookName);
		book.setIsbnNumber(isbnNumber);
		bookRepository.save(book);
		return "Details got Saved";
	}

	@PostMapping(path = "/updatebook")
	public @ResponseBody String updateBooks(@RequestParam Integer id, @RequestParam String bookName, @RequestParam String isbnNumber) {
		Book existingBook = bookRepository.findById(id).orElseGet(null);
		existingBook.setBookName(bookName);
		existingBook.setIsbnNumber(isbnNumber);
		bookRepository.save(existingBook);
		return "Details got Updated";
	}

	@DeleteMapping(path = "/deletebook")
	public @ResponseBody String deleteBooks(@RequestParam Integer id) {
		try
		{
			bookRepository.deleteById(id);
			return "Details got deleted";
		}
		catch (Exception e)
		{
			return "Error while deleting record : " + e.getMessage();
		}
	}
	
	@GetMapping(path = "/books")
	public @ResponseBody Iterable<Book> getAllUsers() {
		return bookRepository.findAll();
	}
}
