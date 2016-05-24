/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package movieservice;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2016-05-21")
public class Collection implements org.apache.thrift.TBase<Collection, Collection._Fields>, java.io.Serializable, Cloneable, Comparable<Collection> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Collection");

  private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField("id", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("name", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField POSTER_PATH_FIELD_DESC = new org.apache.thrift.protocol.TField("poster_path", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField BACKDROP_PATH_FIELD_DESC = new org.apache.thrift.protocol.TField("backdrop_path", org.apache.thrift.protocol.TType.STRING, (short)4);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new CollectionStandardSchemeFactory());
    schemes.put(TupleScheme.class, new CollectionTupleSchemeFactory());
  }

  public long id; // required
  public String name; // required
  public String poster_path; // required
  public String backdrop_path; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ID((short)1, "id"),
    NAME((short)2, "name"),
    POSTER_PATH((short)3, "poster_path"),
    BACKDROP_PATH((short)4, "backdrop_path");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // ID
          return ID;
        case 2: // NAME
          return NAME;
        case 3: // POSTER_PATH
          return POSTER_PATH;
        case 4: // BACKDROP_PATH
          return BACKDROP_PATH;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __ID_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ID, new org.apache.thrift.meta_data.FieldMetaData("id", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.NAME, new org.apache.thrift.meta_data.FieldMetaData("name", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.POSTER_PATH, new org.apache.thrift.meta_data.FieldMetaData("poster_path", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.BACKDROP_PATH, new org.apache.thrift.meta_data.FieldMetaData("backdrop_path", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Collection.class, metaDataMap);
  }

  public Collection() {
  }

  public Collection(
    long id,
    String name,
    String poster_path,
    String backdrop_path)
  {
    this();
    this.id = id;
    setIdIsSet(true);
    this.name = name;
    this.poster_path = poster_path;
    this.backdrop_path = backdrop_path;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Collection(Collection other) {
    __isset_bitfield = other.__isset_bitfield;
    this.id = other.id;
    if (other.isSetName()) {
      this.name = other.name;
    }
    if (other.isSetPoster_path()) {
      this.poster_path = other.poster_path;
    }
    if (other.isSetBackdrop_path()) {
      this.backdrop_path = other.backdrop_path;
    }
  }

  public Collection deepCopy() {
    return new Collection(this);
  }

  @Override
  public void clear() {
    setIdIsSet(false);
    this.id = 0;
    this.name = null;
    this.poster_path = null;
    this.backdrop_path = null;
  }

  public long getId() {
    return this.id;
  }

  public Collection setId(long id) {
    this.id = id;
    setIdIsSet(true);
    return this;
  }

  public void unsetId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __ID_ISSET_ID);
  }

  /** Returns true if field id is set (has been assigned a value) and false otherwise */
  public boolean isSetId() {
    return EncodingUtils.testBit(__isset_bitfield, __ID_ISSET_ID);
  }

  public void setIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __ID_ISSET_ID, value);
  }

  public String getName() {
    return this.name;
  }

  public Collection setName(String name) {
    this.name = name;
    return this;
  }

  public void unsetName() {
    this.name = null;
  }

  /** Returns true if field name is set (has been assigned a value) and false otherwise */
  public boolean isSetName() {
    return this.name != null;
  }

  public void setNameIsSet(boolean value) {
    if (!value) {
      this.name = null;
    }
  }

  public String getPoster_path() {
    return this.poster_path;
  }

  public Collection setPoster_path(String poster_path) {
    this.poster_path = poster_path;
    return this;
  }

  public void unsetPoster_path() {
    this.poster_path = null;
  }

  /** Returns true if field poster_path is set (has been assigned a value) and false otherwise */
  public boolean isSetPoster_path() {
    return this.poster_path != null;
  }

  public void setPoster_pathIsSet(boolean value) {
    if (!value) {
      this.poster_path = null;
    }
  }

  public String getBackdrop_path() {
    return this.backdrop_path;
  }

  public Collection setBackdrop_path(String backdrop_path) {
    this.backdrop_path = backdrop_path;
    return this;
  }

  public void unsetBackdrop_path() {
    this.backdrop_path = null;
  }

  /** Returns true if field backdrop_path is set (has been assigned a value) and false otherwise */
  public boolean isSetBackdrop_path() {
    return this.backdrop_path != null;
  }

  public void setBackdrop_pathIsSet(boolean value) {
    if (!value) {
      this.backdrop_path = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case ID:
      if (value == null) {
        unsetId();
      } else {
        setId((Long)value);
      }
      break;

    case NAME:
      if (value == null) {
        unsetName();
      } else {
        setName((String)value);
      }
      break;

    case POSTER_PATH:
      if (value == null) {
        unsetPoster_path();
      } else {
        setPoster_path((String)value);
      }
      break;

    case BACKDROP_PATH:
      if (value == null) {
        unsetBackdrop_path();
      } else {
        setBackdrop_path((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ID:
      return getId();

    case NAME:
      return getName();

    case POSTER_PATH:
      return getPoster_path();

    case BACKDROP_PATH:
      return getBackdrop_path();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case ID:
      return isSetId();
    case NAME:
      return isSetName();
    case POSTER_PATH:
      return isSetPoster_path();
    case BACKDROP_PATH:
      return isSetBackdrop_path();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof Collection)
      return this.equals((Collection)that);
    return false;
  }

  public boolean equals(Collection that) {
    if (that == null)
      return false;

    boolean this_present_id = true;
    boolean that_present_id = true;
    if (this_present_id || that_present_id) {
      if (!(this_present_id && that_present_id))
        return false;
      if (this.id != that.id)
        return false;
    }

    boolean this_present_name = true && this.isSetName();
    boolean that_present_name = true && that.isSetName();
    if (this_present_name || that_present_name) {
      if (!(this_present_name && that_present_name))
        return false;
      if (!this.name.equals(that.name))
        return false;
    }

    boolean this_present_poster_path = true && this.isSetPoster_path();
    boolean that_present_poster_path = true && that.isSetPoster_path();
    if (this_present_poster_path || that_present_poster_path) {
      if (!(this_present_poster_path && that_present_poster_path))
        return false;
      if (!this.poster_path.equals(that.poster_path))
        return false;
    }

    boolean this_present_backdrop_path = true && this.isSetBackdrop_path();
    boolean that_present_backdrop_path = true && that.isSetBackdrop_path();
    if (this_present_backdrop_path || that_present_backdrop_path) {
      if (!(this_present_backdrop_path && that_present_backdrop_path))
        return false;
      if (!this.backdrop_path.equals(that.backdrop_path))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_id = true;
    list.add(present_id);
    if (present_id)
      list.add(id);

    boolean present_name = true && (isSetName());
    list.add(present_name);
    if (present_name)
      list.add(name);

    boolean present_poster_path = true && (isSetPoster_path());
    list.add(present_poster_path);
    if (present_poster_path)
      list.add(poster_path);

    boolean present_backdrop_path = true && (isSetBackdrop_path());
    list.add(present_backdrop_path);
    if (present_backdrop_path)
      list.add(backdrop_path);

    return list.hashCode();
  }

  @Override
  public int compareTo(Collection other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetId()).compareTo(other.isSetId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.id, other.id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetName()).compareTo(other.isSetName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.name, other.name);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetPoster_path()).compareTo(other.isSetPoster_path());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPoster_path()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.poster_path, other.poster_path);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetBackdrop_path()).compareTo(other.isSetBackdrop_path());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetBackdrop_path()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.backdrop_path, other.backdrop_path);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Collection(");
    boolean first = true;

    sb.append("id:");
    sb.append(this.id);
    first = false;
    if (!first) sb.append(", ");
    sb.append("name:");
    if (this.name == null) {
      sb.append("null");
    } else {
      sb.append(this.name);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("poster_path:");
    if (this.poster_path == null) {
      sb.append("null");
    } else {
      sb.append(this.poster_path);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("backdrop_path:");
    if (this.backdrop_path == null) {
      sb.append("null");
    } else {
      sb.append(this.backdrop_path);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class CollectionStandardSchemeFactory implements SchemeFactory {
    public CollectionStandardScheme getScheme() {
      return new CollectionStandardScheme();
    }
  }

  private static class CollectionStandardScheme extends StandardScheme<Collection> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Collection struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.id = iprot.readI64();
              struct.setIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.name = iprot.readString();
              struct.setNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // POSTER_PATH
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.poster_path = iprot.readString();
              struct.setPoster_pathIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // BACKDROP_PATH
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.backdrop_path = iprot.readString();
              struct.setBackdrop_pathIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, Collection struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(ID_FIELD_DESC);
      oprot.writeI64(struct.id);
      oprot.writeFieldEnd();
      if (struct.name != null) {
        oprot.writeFieldBegin(NAME_FIELD_DESC);
        oprot.writeString(struct.name);
        oprot.writeFieldEnd();
      }
      if (struct.poster_path != null) {
        oprot.writeFieldBegin(POSTER_PATH_FIELD_DESC);
        oprot.writeString(struct.poster_path);
        oprot.writeFieldEnd();
      }
      if (struct.backdrop_path != null) {
        oprot.writeFieldBegin(BACKDROP_PATH_FIELD_DESC);
        oprot.writeString(struct.backdrop_path);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class CollectionTupleSchemeFactory implements SchemeFactory {
    public CollectionTupleScheme getScheme() {
      return new CollectionTupleScheme();
    }
  }

  private static class CollectionTupleScheme extends TupleScheme<Collection> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Collection struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetId()) {
        optionals.set(0);
      }
      if (struct.isSetName()) {
        optionals.set(1);
      }
      if (struct.isSetPoster_path()) {
        optionals.set(2);
      }
      if (struct.isSetBackdrop_path()) {
        optionals.set(3);
      }
      oprot.writeBitSet(optionals, 4);
      if (struct.isSetId()) {
        oprot.writeI64(struct.id);
      }
      if (struct.isSetName()) {
        oprot.writeString(struct.name);
      }
      if (struct.isSetPoster_path()) {
        oprot.writeString(struct.poster_path);
      }
      if (struct.isSetBackdrop_path()) {
        oprot.writeString(struct.backdrop_path);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Collection struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(4);
      if (incoming.get(0)) {
        struct.id = iprot.readI64();
        struct.setIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.name = iprot.readString();
        struct.setNameIsSet(true);
      }
      if (incoming.get(2)) {
        struct.poster_path = iprot.readString();
        struct.setPoster_pathIsSet(true);
      }
      if (incoming.get(3)) {
        struct.backdrop_path = iprot.readString();
        struct.setBackdrop_pathIsSet(true);
      }
    }
  }

}

