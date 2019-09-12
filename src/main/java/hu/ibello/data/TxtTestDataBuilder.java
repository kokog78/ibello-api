package hu.ibello.data;

/**
 * <p>
 * This interface specifies a test data loader which loads text from a file.
 * </p>
 * <p>
 * The main method of this interface is the {@link #loadString()}. It returns the loaded text.
 * The {@link #openStream()} method opens a stream which contains this text.
 * </p>
 * <p>
 * By default, the file is loaded with UTF-8 character set. We can change this with the {@link #withCharset(java.nio.charset.Charset)}
 * method.
 * </p>
 * @see TestDataBuilder#fromTxt(String)
 * @author Kornél Simon
 *
 */
public interface TxtTestDataBuilder extends StringBasedBuilder<TxtTestDataBuilder> {

}
