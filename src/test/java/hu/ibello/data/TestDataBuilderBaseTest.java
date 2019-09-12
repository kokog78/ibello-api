package hu.ibello.data;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.junit.Test;

public class TestDataBuilderBaseTest {

	private String content;
	private StringBasedBuilder<?> base = new StringBasedBuilder() {
		
		@Override
		public String loadString() {
			return content;
		}
		
		@Override
		public StringBasedBuilder<?> withCharset(Charset charset) {
			return base;
		}
	};
	
	@Test
	public void openStream_should_open_stream_for_content() throws Exception {
		content = "árvíztűrő tükörfúrógép";
		try (InputStream etalon = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8)); InputStream stream = base.openStream()) {
			assertThat(stream).hasSameContentAs(etalon);
		}
	}
	
	@Test
	public void openStream_should_return_null_if_content_does_not_exist() throws Exception {
		content = null;
		InputStream stream = base.openStream();
		assertThat(stream).isNull();
	}
	
}
