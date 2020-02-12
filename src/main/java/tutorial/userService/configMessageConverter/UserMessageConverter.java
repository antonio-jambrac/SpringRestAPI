package tutorial.userService.configMessageConverter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import tutorial.userService.model.User;

public class UserMessageConverter extends AbstractHttpMessageConverter<User> {

	public UserMessageConverter() {
		super(new MediaType("text", "user"));
	}
	
	@Override
	protected boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	protected User readInternal(Class<? extends User> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		
		String requestMessage = toUserMessage(inputMessage.getBody());
		int i = requestMessage.indexOf("\n");
			if(i==-1) {
				throw new HttpMessageNotReadableException("No first Line found", inputMessage);
			}
		String email = requestMessage.substring(0, i).trim();
		String firstname = requestMessage.substring(i).split(" ")[0].trim();
		String lastname = requestMessage.substring(i).split(" ")[1].trim();
		User user = new User();
		user.setEmail(email);
		user.setFirstname(firstname);
		user.setLastname(lastname);
		return user;
	}

	private String toUserMessage(InputStream body) {
		Scanner scanner = new Scanner(body, "UTF-8");
		return scanner.useDelimiter("\\A").next();
	}

	@Override
	protected void writeInternal(User user, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		OutputStream outputStream = outputMessage.getBody();
		String body = user.getEmail()+"\n"+user.getFirstname()+" "+user.getLastname();
		outputStream.write(body.getBytes());
		outputStream.close();
	}

}
