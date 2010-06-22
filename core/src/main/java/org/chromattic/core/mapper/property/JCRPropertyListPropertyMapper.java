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

package org.chromattic.core.mapper.property;

import org.chromattic.core.ListType;
import org.chromattic.core.ObjectContext;
import org.chromattic.core.mapper.PropertyMapper;
import org.chromattic.core.vt2.ValueDefinition;
import org.chromattic.metamodel.bean.SimpleValueInfo;
import org.chromattic.metamodel.bean.MultiValuedPropertyInfo;
import org.chromattic.metamodel.mapping.jcr.PropertyMetaType;
import org.chromattic.metamodel.mapping2.ValueMapping;
import org.chromattic.spi.type.SimpleTypeProvider;

import java.util.List;

/**
 * @author <a href="mailto:julien.viet@exoplatform.com">Julien Viet</a>
 * @version $Revision$
 */
public class JCRPropertyListPropertyMapper<O extends ObjectContext, E, I>
  extends PropertyMapper<MultiValuedPropertyInfo<SimpleValueInfo>, SimpleValueInfo, O> {

  /** . */
  private final String jcrPropertyName;

  /** . */
  private final ListType listType;

  /** . */
  private final SimpleValueInfo elementType;

  /** . */
  private final ValueDefinition<I, E> vt;

  public JCRPropertyListPropertyMapper(
    Class<O> contextType,
    SimpleTypeProvider<I, E> vt,
    ValueMapping.Multi info) {
    super(contextType, info);

    //
    ListType listType;
    switch (info.getProperty().getKind()) {
      case ARRAY:
        listType = ListType.ARRAY;
        break;
      case LIST:
        listType = ListType.LIST;
        break;
      default:
        throw new AssertionError();
    }

    //
    this.listType = listType;
    this.jcrPropertyName = info.getPropertyDefinition().getName();
    this.elementType = info.getValue();
    this.vt = new ValueDefinition<I, E>((Class)info.getValue().getEffectiveType().unwrap(), (PropertyMetaType<I>) info.getPropertyDefinition().getMetaType(), vt, info.getPropertyDefinition().getDefaultValue());
  }

  @Override
  public Object get(O context) throws Throwable {
    List<E> list = context.getPropertyValues(jcrPropertyName, vt, listType);
    return list == null ? null : listType.unwrap(vt, list);
  }

  @Override
  public void set(O context, Object value) throws Throwable {
    List<E> list = value == null ? null : listType.wrap(vt, value);
    context.setPropertyValues(jcrPropertyName, vt, listType, list);
  }
}