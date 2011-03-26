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
package org.chromattic.test.property.defaultvalue;

import org.chromattic.api.annotations.DefaultValue;
import org.chromattic.api.annotations.NodeMapping;
import org.chromattic.api.annotations.Property;

/**
 * @author <a href="mailto:julien.viet@exoplatform.com">Julien Viet</a>
 * @version $Revision$
 */
@NodeMapping(name = "tp_a")
public abstract class A {

  @DefaultValue.Int({})
  @Property(name = "primitive_int_property")
  public abstract int getPrimitiveInt1();

  public abstract void setPrimitiveInt1(int s);

  @DefaultValue.Int(5)
  @Property(name = "primitive_int_property")
  public abstract int getPrimitiveInt2();

  public abstract void setPrimitiveInt2(int s);

  @DefaultValue.Int({5,7})
  @Property(name = "primitive_int_property")
  public abstract int getPrimitiveInt3();

  public abstract void setPrimitiveInt3(int s);

}