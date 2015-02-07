/**
 * Hier wird was geändert.
 * Toll, mal sehen was noch so alles geht! 
 */

package pub.Demo.Tray;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.ImageIcon;

public class ResourceReader
{
  public Image loadImageResource(String pkgname, String fname)
  {
    Image ret = null;
    InputStream is = null;

    try{
      is = getResourceStream(pkgname, fname);
      if (is != null) {
        byte[] buffer = new byte[0];
        byte[] tmpbuf = new byte[1024];
        while (true) {
          int len = is.read(tmpbuf);
          if (len <= 0) {
            break;
          }
          byte[] newbuf = new byte[buffer.length + len];
          System.arraycopy(buffer, 0, newbuf, 0, buffer.length);
          System.arraycopy(tmpbuf, 0, newbuf, buffer.length, len);
          buffer = newbuf;
        }
        ret = Toolkit.getDefaultToolkit().createImage(buffer);
        is.close();
      }
      else {}
    } catch (IOException ioe) {
      ret = null;
    }

    return ret;
  }

  public ImageIcon loadImageIconResource(String pkgname, String fname)
  {
    Image img = null;

    img = this.loadImageResource(pkgname, fname);

    if(img == null) return null;
    return new ImageIcon(img);
  }

  public String loadTextResource(String pkgname, String fname)
  {
    String ret = null;
    InputStream is = null;

    try{
      is = getResourceStream(pkgname, fname);
      if (is != null) {
        StringBuffer sb = new StringBuffer();
        while (true) {
          int c = is.read();
          if (c == -1) {
            break;
          }
          sb.append((char)c);
        }
        is.close();
        ret = sb.toString();
      }
      else{}
    } catch (IOException ioe) {
      ret = null;
    }
    return ret;
  }

  public InputStream getResourceStream(String pkgname, String fname)
  {
    String resname = "";
    
    if(pkgname == null) 
      resname = "/" + fname;
    else  
     resname = "/" + pkgname.replace('.', '/') + "/" + fname;
    
    Class clazz = this.getClass();
    InputStream is = clazz.getResourceAsStream(resname);
    return is;
  }
}
