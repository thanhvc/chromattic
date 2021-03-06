/*
 * Copyright (C) 2003-2009 eXo Platform SAS.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.chromattic.ext.ntdef;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * @author <a href="mailto:julien.viet@exoplatform.com">Julien Viet</a>
 * @version $Revision$
 */
public class Resource {

  public static Resource createPlainText(String text) {
    try {
      return new Resource("text/plain", "UTF-8", text.getBytes("UTF-8"));
    }
    catch (UnsupportedEncodingException e) {
      throw new UndeclaredThrowableException(e);
    }
  }

  /** . */
  private String mimeType;

  /** . */
  private String encoding;

  /** . */
  private byte[] data;

  public Resource(String mimeType, String encoding, byte[] data) {
    this.mimeType = mimeType;
    this.encoding = encoding;
    this.data = data;
  }

  public Resource(String mimeType, String encoding, String data) throws UnsupportedEncodingException {
    this(mimeType, encoding, data.getBytes(encoding));
  }

  public Resource(String mimeType, String data) throws UnsupportedEncodingException {
    this(mimeType, "UTF8", data);
  }

  public String getMimeType() {
    return mimeType;
  }

  public String getEncoding() {
    return encoding;
  }

  public byte[] getData() {
    return data;
  }
}
