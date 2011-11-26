package nl.scouting.hit.sitecreator;


public class JNLPTest {
	// private final class SwingFileOpenService implements FileOpenService {
	// @Override
	// public FileContents openFileDialog(final String arg0,
	// final String[] arg1) throws IOException {
	// final JFileChooser fc = new JFileChooser(arg0);
	// fc.showOpenDialog(null);
	// final File file = fc.getSelectedFile();
	// return new FileContents() {
	// private long maxLength;
	//
	// @Override
	// public long setMaxLength(final long arg0) throws IOException {
	// maxLength = arg0;
	// return maxLength;
	// }
	//
	// @Override
	// public JNLPRandomAccessFile getRandomAccessFile(
	// final String arg0) throws IOException {
	// return null;
	// }
	//
	// @Override
	// public OutputStream getOutputStream(final boolean append)
	// throws IOException {
	// return new FileOutputStream(file, append);
	// }
	//
	// @Override
	// public String getName() throws IOException {
	// return file.getName();
	// }
	//
	// @Override
	// public long getMaxLength() throws IOException {
	// return maxLength;
	// }
	//
	// @Override
	// public long getLength() throws IOException {
	// return file.length();
	// }
	//
	// @Override
	// public InputStream getInputStream() throws IOException {
	// return new FileInputStream(file);
	// }
	//
	// @Override
	// public boolean canWrite() throws IOException {
	// return file.canWrite();
	// }
	//
	// @Override
	// public boolean canRead() throws IOException {
	// return file.canRead();
	// }
	// };
	// }
	//
	// @Override
	// public FileContents[] openMultiFileDialog(final String arg0,
	// final String[] arg1) throws IOException {
	// // TODO Auto-generated method stub
	// return null;
	// }
	// }
	//
	// @Ignore
	// @Test
	// public void testName() throws Exception {
	// final ServiceManagerStub stub = new ServiceManagerStub() {
	//
	// @Override
	// public Object lookup(final String serviceName)
	// throws UnavailableServiceException {
	// final Map<String, Object> map = new HashMap<String, Object>();
	// map.put("javax.jnlp.FileOpenService",
	// new SwingFileOpenService());
	// return map.get(serviceName);
	// }
	//
	// @Override
	// public String[] getServiceNames() {
	// return new String[] { "javax.jnlp.FileOpenService" };
	// }
	// };
	// ServiceManager.setServiceManagerStub(stub);
	// final FileOpenService fos = (FileOpenService) ServiceManager
	// .lookup("javax.jnlp.FileOpenService");
	// final FileContents fc = fos.openFileDialog("/tmp",
	// new String[] { "*.txt" });
	// System.out.println(fc.getLength());
	// System.out.println(fc.getName());
	// final byte[] buf = new byte[8192];
	// final int read = fc.getInputStream().read(buf);
	// System.out.println(new String(buf, 0, read));
	//
	// final FileContents[] fcs = fos.openMultiFileDialog(null, null);
	// }
}
