#
# Autogenerated by Thrift Compiler (0.9.3)
#
# DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
#
#  options string: py
#

from thrift.Thrift import TType, TMessageType, TException, TApplicationException

from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol, TProtocol
try:
  from thrift.protocol import fastbinary
except:
  fastbinary = None



class Collection:
  """
  Attributes:
   - id
   - name
   - poster_path
   - backdrop_path
  """

  thrift_spec = (
    None, # 0
    (1, TType.I64, 'id', None, None, ), # 1
    (2, TType.STRING, 'name', None, None, ), # 2
    (3, TType.STRING, 'poster_path', None, None, ), # 3
    (4, TType.STRING, 'backdrop_path', None, None, ), # 4
  )

  def __init__(self, id=None, name=None, poster_path=None, backdrop_path=None,):
    self.id = id
    self.name = name
    self.poster_path = poster_path
    self.backdrop_path = backdrop_path

  def read(self, iprot):
    if iprot.__class__ == TBinaryProtocol.TBinaryProtocolAccelerated and isinstance(iprot.trans, TTransport.CReadableTransport) and self.thrift_spec is not None and fastbinary is not None:
      fastbinary.decode_binary(self, iprot.trans, (self.__class__, self.thrift_spec))
      return
    iprot.readStructBegin()
    while True:
      (fname, ftype, fid) = iprot.readFieldBegin()
      if ftype == TType.STOP:
        break
      if fid == 1:
        if ftype == TType.I64:
          self.id = iprot.readI64()
        else:
          iprot.skip(ftype)
      elif fid == 2:
        if ftype == TType.STRING:
          self.name = iprot.readString()
        else:
          iprot.skip(ftype)
      elif fid == 3:
        if ftype == TType.STRING:
          self.poster_path = iprot.readString()
        else:
          iprot.skip(ftype)
      elif fid == 4:
        if ftype == TType.STRING:
          self.backdrop_path = iprot.readString()
        else:
          iprot.skip(ftype)
      else:
        iprot.skip(ftype)
      iprot.readFieldEnd()
    iprot.readStructEnd()

  def write(self, oprot):
    if oprot.__class__ == TBinaryProtocol.TBinaryProtocolAccelerated and self.thrift_spec is not None and fastbinary is not None:
      oprot.trans.write(fastbinary.encode_binary(self, (self.__class__, self.thrift_spec)))
      return
    oprot.writeStructBegin('Collection')
    if self.id is not None:
      oprot.writeFieldBegin('id', TType.I64, 1)
      oprot.writeI64(self.id)
      oprot.writeFieldEnd()
    if self.name is not None:
      oprot.writeFieldBegin('name', TType.STRING, 2)
      oprot.writeString(self.name)
      oprot.writeFieldEnd()
    if self.poster_path is not None:
      oprot.writeFieldBegin('poster_path', TType.STRING, 3)
      oprot.writeString(self.poster_path)
      oprot.writeFieldEnd()
    if self.backdrop_path is not None:
      oprot.writeFieldBegin('backdrop_path', TType.STRING, 4)
      oprot.writeString(self.backdrop_path)
      oprot.writeFieldEnd()
    oprot.writeFieldStop()
    oprot.writeStructEnd()

  def validate(self):
    return


  def __hash__(self):
    value = 17
    value = (value * 31) ^ hash(self.id)
    value = (value * 31) ^ hash(self.name)
    value = (value * 31) ^ hash(self.poster_path)
    value = (value * 31) ^ hash(self.backdrop_path)
    return value

  def __repr__(self):
    L = ['%s=%r' % (key, value)
      for key, value in self.__dict__.iteritems()]
    return '%s(%s)' % (self.__class__.__name__, ', '.join(L))

  def __eq__(self, other):
    return isinstance(other, self.__class__) and self.__dict__ == other.__dict__

  def __ne__(self, other):
    return not (self == other)

class Genre:
  """
  Attributes:
   - id
   - name
  """

  thrift_spec = (
    None, # 0
    (1, TType.I32, 'id', None, None, ), # 1
    (2, TType.STRING, 'name', None, None, ), # 2
  )

  def __init__(self, id=None, name=None,):
    self.id = id
    self.name = name

  def read(self, iprot):
    if iprot.__class__ == TBinaryProtocol.TBinaryProtocolAccelerated and isinstance(iprot.trans, TTransport.CReadableTransport) and self.thrift_spec is not None and fastbinary is not None:
      fastbinary.decode_binary(self, iprot.trans, (self.__class__, self.thrift_spec))
      return
    iprot.readStructBegin()
    while True:
      (fname, ftype, fid) = iprot.readFieldBegin()
      if ftype == TType.STOP:
        break
      if fid == 1:
        if ftype == TType.I32:
          self.id = iprot.readI32()
        else:
          iprot.skip(ftype)
      elif fid == 2:
        if ftype == TType.STRING:
          self.name = iprot.readString()
        else:
          iprot.skip(ftype)
      else:
        iprot.skip(ftype)
      iprot.readFieldEnd()
    iprot.readStructEnd()

  def write(self, oprot):
    if oprot.__class__ == TBinaryProtocol.TBinaryProtocolAccelerated and self.thrift_spec is not None and fastbinary is not None:
      oprot.trans.write(fastbinary.encode_binary(self, (self.__class__, self.thrift_spec)))
      return
    oprot.writeStructBegin('Genre')
    if self.id is not None:
      oprot.writeFieldBegin('id', TType.I32, 1)
      oprot.writeI32(self.id)
      oprot.writeFieldEnd()
    if self.name is not None:
      oprot.writeFieldBegin('name', TType.STRING, 2)
      oprot.writeString(self.name)
      oprot.writeFieldEnd()
    oprot.writeFieldStop()
    oprot.writeStructEnd()

  def validate(self):
    return


  def __hash__(self):
    value = 17
    value = (value * 31) ^ hash(self.id)
    value = (value * 31) ^ hash(self.name)
    return value

  def __repr__(self):
    L = ['%s=%r' % (key, value)
      for key, value in self.__dict__.iteritems()]
    return '%s(%s)' % (self.__class__.__name__, ', '.join(L))

  def __eq__(self, other):
    return isinstance(other, self.__class__) and self.__dict__ == other.__dict__

  def __ne__(self, other):
    return not (self == other)

class ProductionCompany:
  """
  Attributes:
   - id
   - name
  """

  thrift_spec = (
    None, # 0
    (1, TType.I32, 'id', None, None, ), # 1
    (2, TType.STRING, 'name', None, None, ), # 2
  )

  def __init__(self, id=None, name=None,):
    self.id = id
    self.name = name

  def read(self, iprot):
    if iprot.__class__ == TBinaryProtocol.TBinaryProtocolAccelerated and isinstance(iprot.trans, TTransport.CReadableTransport) and self.thrift_spec is not None and fastbinary is not None:
      fastbinary.decode_binary(self, iprot.trans, (self.__class__, self.thrift_spec))
      return
    iprot.readStructBegin()
    while True:
      (fname, ftype, fid) = iprot.readFieldBegin()
      if ftype == TType.STOP:
        break
      if fid == 1:
        if ftype == TType.I32:
          self.id = iprot.readI32()
        else:
          iprot.skip(ftype)
      elif fid == 2:
        if ftype == TType.STRING:
          self.name = iprot.readString()
        else:
          iprot.skip(ftype)
      else:
        iprot.skip(ftype)
      iprot.readFieldEnd()
    iprot.readStructEnd()

  def write(self, oprot):
    if oprot.__class__ == TBinaryProtocol.TBinaryProtocolAccelerated and self.thrift_spec is not None and fastbinary is not None:
      oprot.trans.write(fastbinary.encode_binary(self, (self.__class__, self.thrift_spec)))
      return
    oprot.writeStructBegin('ProductionCompany')
    if self.id is not None:
      oprot.writeFieldBegin('id', TType.I32, 1)
      oprot.writeI32(self.id)
      oprot.writeFieldEnd()
    if self.name is not None:
      oprot.writeFieldBegin('name', TType.STRING, 2)
      oprot.writeString(self.name)
      oprot.writeFieldEnd()
    oprot.writeFieldStop()
    oprot.writeStructEnd()

  def validate(self):
    return


  def __hash__(self):
    value = 17
    value = (value * 31) ^ hash(self.id)
    value = (value * 31) ^ hash(self.name)
    return value

  def __repr__(self):
    L = ['%s=%r' % (key, value)
      for key, value in self.__dict__.iteritems()]
    return '%s(%s)' % (self.__class__.__name__, ', '.join(L))

  def __eq__(self, other):
    return isinstance(other, self.__class__) and self.__dict__ == other.__dict__

  def __ne__(self, other):
    return not (self == other)

class ProductionCountry:
  """
  Attributes:
   - iso_3166_1
   - name
  """

  thrift_spec = (
    None, # 0
    (1, TType.STRING, 'iso_3166_1', None, None, ), # 1
    (2, TType.STRING, 'name', None, None, ), # 2
  )

  def __init__(self, iso_3166_1=None, name=None,):
    self.iso_3166_1 = iso_3166_1
    self.name = name

  def read(self, iprot):
    if iprot.__class__ == TBinaryProtocol.TBinaryProtocolAccelerated and isinstance(iprot.trans, TTransport.CReadableTransport) and self.thrift_spec is not None and fastbinary is not None:
      fastbinary.decode_binary(self, iprot.trans, (self.__class__, self.thrift_spec))
      return
    iprot.readStructBegin()
    while True:
      (fname, ftype, fid) = iprot.readFieldBegin()
      if ftype == TType.STOP:
        break
      if fid == 1:
        if ftype == TType.STRING:
          self.iso_3166_1 = iprot.readString()
        else:
          iprot.skip(ftype)
      elif fid == 2:
        if ftype == TType.STRING:
          self.name = iprot.readString()
        else:
          iprot.skip(ftype)
      else:
        iprot.skip(ftype)
      iprot.readFieldEnd()
    iprot.readStructEnd()

  def write(self, oprot):
    if oprot.__class__ == TBinaryProtocol.TBinaryProtocolAccelerated and self.thrift_spec is not None and fastbinary is not None:
      oprot.trans.write(fastbinary.encode_binary(self, (self.__class__, self.thrift_spec)))
      return
    oprot.writeStructBegin('ProductionCountry')
    if self.iso_3166_1 is not None:
      oprot.writeFieldBegin('iso_3166_1', TType.STRING, 1)
      oprot.writeString(self.iso_3166_1)
      oprot.writeFieldEnd()
    if self.name is not None:
      oprot.writeFieldBegin('name', TType.STRING, 2)
      oprot.writeString(self.name)
      oprot.writeFieldEnd()
    oprot.writeFieldStop()
    oprot.writeStructEnd()

  def validate(self):
    return


  def __hash__(self):
    value = 17
    value = (value * 31) ^ hash(self.iso_3166_1)
    value = (value * 31) ^ hash(self.name)
    return value

  def __repr__(self):
    L = ['%s=%r' % (key, value)
      for key, value in self.__dict__.iteritems()]
    return '%s(%s)' % (self.__class__.__name__, ', '.join(L))

  def __eq__(self, other):
    return isinstance(other, self.__class__) and self.__dict__ == other.__dict__

  def __ne__(self, other):
    return not (self == other)

class SpokenLanguage:
  """
  Attributes:
   - iso_639_1
   - name
  """

  thrift_spec = (
    None, # 0
    (1, TType.STRING, 'iso_639_1', None, None, ), # 1
    (2, TType.STRING, 'name', None, None, ), # 2
  )

  def __init__(self, iso_639_1=None, name=None,):
    self.iso_639_1 = iso_639_1
    self.name = name

  def read(self, iprot):
    if iprot.__class__ == TBinaryProtocol.TBinaryProtocolAccelerated and isinstance(iprot.trans, TTransport.CReadableTransport) and self.thrift_spec is not None and fastbinary is not None:
      fastbinary.decode_binary(self, iprot.trans, (self.__class__, self.thrift_spec))
      return
    iprot.readStructBegin()
    while True:
      (fname, ftype, fid) = iprot.readFieldBegin()
      if ftype == TType.STOP:
        break
      if fid == 1:
        if ftype == TType.STRING:
          self.iso_639_1 = iprot.readString()
        else:
          iprot.skip(ftype)
      elif fid == 2:
        if ftype == TType.STRING:
          self.name = iprot.readString()
        else:
          iprot.skip(ftype)
      else:
        iprot.skip(ftype)
      iprot.readFieldEnd()
    iprot.readStructEnd()

  def write(self, oprot):
    if oprot.__class__ == TBinaryProtocol.TBinaryProtocolAccelerated and self.thrift_spec is not None and fastbinary is not None:
      oprot.trans.write(fastbinary.encode_binary(self, (self.__class__, self.thrift_spec)))
      return
    oprot.writeStructBegin('SpokenLanguage')
    if self.iso_639_1 is not None:
      oprot.writeFieldBegin('iso_639_1', TType.STRING, 1)
      oprot.writeString(self.iso_639_1)
      oprot.writeFieldEnd()
    if self.name is not None:
      oprot.writeFieldBegin('name', TType.STRING, 2)
      oprot.writeString(self.name)
      oprot.writeFieldEnd()
    oprot.writeFieldStop()
    oprot.writeStructEnd()

  def validate(self):
    return


  def __hash__(self):
    value = 17
    value = (value * 31) ^ hash(self.iso_639_1)
    value = (value * 31) ^ hash(self.name)
    return value

  def __repr__(self):
    L = ['%s=%r' % (key, value)
      for key, value in self.__dict__.iteritems()]
    return '%s(%s)' % (self.__class__.__name__, ', '.join(L))

  def __eq__(self, other):
    return isinstance(other, self.__class__) and self.__dict__ == other.__dict__

  def __ne__(self, other):
    return not (self == other)

class Movie:
  """
  Attributes:
   - adult
   - backdrop_path
   - belongs_to_collection
   - budget
   - genres
   - homepage
   - id
   - imdb_id
   - original_language
   - original_title
   - overview
   - popularity
   - poster_path
   - production_companies
   - production_countries
   - release_date
   - revenue
   - runtime
   - spoken_languages
   - status
   - tagline
   - title
   - video
   - vote_average
   - vote_count
  """

  thrift_spec = (
    None, # 0
    (1, TType.BOOL, 'adult', None, None, ), # 1
    (2, TType.STRING, 'backdrop_path', None, None, ), # 2
    (3, TType.STRUCT, 'belongs_to_collection', (Collection, Collection.thrift_spec), None, ), # 3
    (4, TType.I64, 'budget', None, None, ), # 4
    (5, TType.LIST, 'genres', (TType.STRUCT,(Genre, Genre.thrift_spec)), None, ), # 5
    (6, TType.STRING, 'homepage', None, None, ), # 6
    (7, TType.I64, 'id', None, None, ), # 7
    (8, TType.STRING, 'imdb_id', None, None, ), # 8
    (9, TType.STRING, 'original_language', None, None, ), # 9
    (10, TType.STRING, 'original_title', None, None, ), # 10
    (11, TType.STRING, 'overview', None, None, ), # 11
    (12, TType.DOUBLE, 'popularity', None, None, ), # 12
    (13, TType.STRING, 'poster_path', None, None, ), # 13
    (14, TType.LIST, 'production_companies', (TType.STRUCT,(ProductionCompany, ProductionCompany.thrift_spec)), None, ), # 14
    (15, TType.LIST, 'production_countries', (TType.STRUCT,(ProductionCountry, ProductionCountry.thrift_spec)), None, ), # 15
    (16, TType.STRING, 'release_date', None, None, ), # 16
    (17, TType.I64, 'revenue', None, None, ), # 17
    (18, TType.I32, 'runtime', None, None, ), # 18
    (19, TType.LIST, 'spoken_languages', (TType.STRUCT,(SpokenLanguage, SpokenLanguage.thrift_spec)), None, ), # 19
    (20, TType.STRING, 'status', None, None, ), # 20
    (21, TType.STRING, 'tagline', None, None, ), # 21
    (22, TType.STRING, 'title', None, None, ), # 22
    (23, TType.BOOL, 'video', None, None, ), # 23
    (24, TType.DOUBLE, 'vote_average', None, None, ), # 24
    (25, TType.I32, 'vote_count', None, None, ), # 25
  )

  def __init__(self, adult=None, backdrop_path=None, belongs_to_collection=None, budget=None, genres=None, homepage=None, id=None, imdb_id=None, original_language=None, original_title=None, overview=None, popularity=None, poster_path=None, production_companies=None, production_countries=None, release_date=None, revenue=None, runtime=None, spoken_languages=None, status=None, tagline=None, title=None, video=None, vote_average=None, vote_count=None,):
    self.adult = adult
    self.backdrop_path = backdrop_path
    self.belongs_to_collection = belongs_to_collection
    self.budget = budget
    self.genres = genres
    self.homepage = homepage
    self.id = id
    self.imdb_id = imdb_id
    self.original_language = original_language
    self.original_title = original_title
    self.overview = overview
    self.popularity = popularity
    self.poster_path = poster_path
    self.production_companies = production_companies
    self.production_countries = production_countries
    self.release_date = release_date
    self.revenue = revenue
    self.runtime = runtime
    self.spoken_languages = spoken_languages
    self.status = status
    self.tagline = tagline
    self.title = title
    self.video = video
    self.vote_average = vote_average
    self.vote_count = vote_count

  def read(self, iprot):
    if iprot.__class__ == TBinaryProtocol.TBinaryProtocolAccelerated and isinstance(iprot.trans, TTransport.CReadableTransport) and self.thrift_spec is not None and fastbinary is not None:
      fastbinary.decode_binary(self, iprot.trans, (self.__class__, self.thrift_spec))
      return
    iprot.readStructBegin()
    while True:
      (fname, ftype, fid) = iprot.readFieldBegin()
      if ftype == TType.STOP:
        break
      if fid == 1:
        if ftype == TType.BOOL:
          self.adult = iprot.readBool()
        else:
          iprot.skip(ftype)
      elif fid == 2:
        if ftype == TType.STRING:
          self.backdrop_path = iprot.readString()
        else:
          iprot.skip(ftype)
      elif fid == 3:
        if ftype == TType.STRUCT:
          self.belongs_to_collection = Collection()
          self.belongs_to_collection.read(iprot)
        else:
          iprot.skip(ftype)
      elif fid == 4:
        if ftype == TType.I64:
          self.budget = iprot.readI64()
        else:
          iprot.skip(ftype)
      elif fid == 5:
        if ftype == TType.LIST:
          self.genres = []
          (_etype3, _size0) = iprot.readListBegin()
          for _i4 in xrange(_size0):
            _elem5 = Genre()
            _elem5.read(iprot)
            self.genres.append(_elem5)
          iprot.readListEnd()
        else:
          iprot.skip(ftype)
      elif fid == 6:
        if ftype == TType.STRING:
          self.homepage = iprot.readString()
        else:
          iprot.skip(ftype)
      elif fid == 7:
        if ftype == TType.I64:
          self.id = iprot.readI64()
        else:
          iprot.skip(ftype)
      elif fid == 8:
        if ftype == TType.STRING:
          self.imdb_id = iprot.readString()
        else:
          iprot.skip(ftype)
      elif fid == 9:
        if ftype == TType.STRING:
          self.original_language = iprot.readString()
        else:
          iprot.skip(ftype)
      elif fid == 10:
        if ftype == TType.STRING:
          self.original_title = iprot.readString()
        else:
          iprot.skip(ftype)
      elif fid == 11:
        if ftype == TType.STRING:
          self.overview = iprot.readString()
        else:
          iprot.skip(ftype)
      elif fid == 12:
        if ftype == TType.DOUBLE:
          self.popularity = iprot.readDouble()
        else:
          iprot.skip(ftype)
      elif fid == 13:
        if ftype == TType.STRING:
          self.poster_path = iprot.readString()
        else:
          iprot.skip(ftype)
      elif fid == 14:
        if ftype == TType.LIST:
          self.production_companies = []
          (_etype9, _size6) = iprot.readListBegin()
          for _i10 in xrange(_size6):
            _elem11 = ProductionCompany()
            _elem11.read(iprot)
            self.production_companies.append(_elem11)
          iprot.readListEnd()
        else:
          iprot.skip(ftype)
      elif fid == 15:
        if ftype == TType.LIST:
          self.production_countries = []
          (_etype15, _size12) = iprot.readListBegin()
          for _i16 in xrange(_size12):
            _elem17 = ProductionCountry()
            _elem17.read(iprot)
            self.production_countries.append(_elem17)
          iprot.readListEnd()
        else:
          iprot.skip(ftype)
      elif fid == 16:
        if ftype == TType.STRING:
          self.release_date = iprot.readString()
        else:
          iprot.skip(ftype)
      elif fid == 17:
        if ftype == TType.I64:
          self.revenue = iprot.readI64()
        else:
          iprot.skip(ftype)
      elif fid == 18:
        if ftype == TType.I32:
          self.runtime = iprot.readI32()
        else:
          iprot.skip(ftype)
      elif fid == 19:
        if ftype == TType.LIST:
          self.spoken_languages = []
          (_etype21, _size18) = iprot.readListBegin()
          for _i22 in xrange(_size18):
            _elem23 = SpokenLanguage()
            _elem23.read(iprot)
            self.spoken_languages.append(_elem23)
          iprot.readListEnd()
        else:
          iprot.skip(ftype)
      elif fid == 20:
        if ftype == TType.STRING:
          self.status = iprot.readString()
        else:
          iprot.skip(ftype)
      elif fid == 21:
        if ftype == TType.STRING:
          self.tagline = iprot.readString()
        else:
          iprot.skip(ftype)
      elif fid == 22:
        if ftype == TType.STRING:
          self.title = iprot.readString()
        else:
          iprot.skip(ftype)
      elif fid == 23:
        if ftype == TType.BOOL:
          self.video = iprot.readBool()
        else:
          iprot.skip(ftype)
      elif fid == 24:
        if ftype == TType.DOUBLE:
          self.vote_average = iprot.readDouble()
        else:
          iprot.skip(ftype)
      elif fid == 25:
        if ftype == TType.I32:
          self.vote_count = iprot.readI32()
        else:
          iprot.skip(ftype)
      else:
        iprot.skip(ftype)
      iprot.readFieldEnd()
    iprot.readStructEnd()

  def write(self, oprot):
    if oprot.__class__ == TBinaryProtocol.TBinaryProtocolAccelerated and self.thrift_spec is not None and fastbinary is not None:
      oprot.trans.write(fastbinary.encode_binary(self, (self.__class__, self.thrift_spec)))
      return
    oprot.writeStructBegin('Movie')
    if self.adult is not None:
      oprot.writeFieldBegin('adult', TType.BOOL, 1)
      oprot.writeBool(self.adult)
      oprot.writeFieldEnd()
    if self.backdrop_path is not None:
      oprot.writeFieldBegin('backdrop_path', TType.STRING, 2)
      oprot.writeString(self.backdrop_path)
      oprot.writeFieldEnd()
    if self.belongs_to_collection is not None:
      oprot.writeFieldBegin('belongs_to_collection', TType.STRUCT, 3)
      self.belongs_to_collection.write(oprot)
      oprot.writeFieldEnd()
    if self.budget is not None:
      oprot.writeFieldBegin('budget', TType.I64, 4)
      oprot.writeI64(self.budget)
      oprot.writeFieldEnd()
    if self.genres is not None:
      oprot.writeFieldBegin('genres', TType.LIST, 5)
      oprot.writeListBegin(TType.STRUCT, len(self.genres))
      for iter24 in self.genres:
        iter24.write(oprot)
      oprot.writeListEnd()
      oprot.writeFieldEnd()
    if self.homepage is not None:
      oprot.writeFieldBegin('homepage', TType.STRING, 6)
      oprot.writeString(self.homepage)
      oprot.writeFieldEnd()
    if self.id is not None:
      oprot.writeFieldBegin('id', TType.I64, 7)
      oprot.writeI64(self.id)
      oprot.writeFieldEnd()
    if self.imdb_id is not None:
      oprot.writeFieldBegin('imdb_id', TType.STRING, 8)
      oprot.writeString(self.imdb_id)
      oprot.writeFieldEnd()
    if self.original_language is not None:
      oprot.writeFieldBegin('original_language', TType.STRING, 9)
      oprot.writeString(self.original_language)
      oprot.writeFieldEnd()
    if self.original_title is not None:
      oprot.writeFieldBegin('original_title', TType.STRING, 10)
      oprot.writeString(self.original_title)
      oprot.writeFieldEnd()
    if self.overview is not None:
      oprot.writeFieldBegin('overview', TType.STRING, 11)
      oprot.writeString(self.overview)
      oprot.writeFieldEnd()
    if self.popularity is not None:
      oprot.writeFieldBegin('popularity', TType.DOUBLE, 12)
      oprot.writeDouble(self.popularity)
      oprot.writeFieldEnd()
    if self.poster_path is not None:
      oprot.writeFieldBegin('poster_path', TType.STRING, 13)
      oprot.writeString(self.poster_path)
      oprot.writeFieldEnd()
    if self.production_companies is not None:
      oprot.writeFieldBegin('production_companies', TType.LIST, 14)
      oprot.writeListBegin(TType.STRUCT, len(self.production_companies))
      for iter25 in self.production_companies:
        iter25.write(oprot)
      oprot.writeListEnd()
      oprot.writeFieldEnd()
    if self.production_countries is not None:
      oprot.writeFieldBegin('production_countries', TType.LIST, 15)
      oprot.writeListBegin(TType.STRUCT, len(self.production_countries))
      for iter26 in self.production_countries:
        iter26.write(oprot)
      oprot.writeListEnd()
      oprot.writeFieldEnd()
    if self.release_date is not None:
      oprot.writeFieldBegin('release_date', TType.STRING, 16)
      oprot.writeString(self.release_date)
      oprot.writeFieldEnd()
    if self.revenue is not None:
      oprot.writeFieldBegin('revenue', TType.I64, 17)
      oprot.writeI64(self.revenue)
      oprot.writeFieldEnd()
    if self.runtime is not None:
      oprot.writeFieldBegin('runtime', TType.I32, 18)
      oprot.writeI32(self.runtime)
      oprot.writeFieldEnd()
    if self.spoken_languages is not None:
      oprot.writeFieldBegin('spoken_languages', TType.LIST, 19)
      oprot.writeListBegin(TType.STRUCT, len(self.spoken_languages))
      for iter27 in self.spoken_languages:
        iter27.write(oprot)
      oprot.writeListEnd()
      oprot.writeFieldEnd()
    if self.status is not None:
      oprot.writeFieldBegin('status', TType.STRING, 20)
      oprot.writeString(self.status)
      oprot.writeFieldEnd()
    if self.tagline is not None:
      oprot.writeFieldBegin('tagline', TType.STRING, 21)
      oprot.writeString(self.tagline)
      oprot.writeFieldEnd()
    if self.title is not None:
      oprot.writeFieldBegin('title', TType.STRING, 22)
      oprot.writeString(self.title)
      oprot.writeFieldEnd()
    if self.video is not None:
      oprot.writeFieldBegin('video', TType.BOOL, 23)
      oprot.writeBool(self.video)
      oprot.writeFieldEnd()
    if self.vote_average is not None:
      oprot.writeFieldBegin('vote_average', TType.DOUBLE, 24)
      oprot.writeDouble(self.vote_average)
      oprot.writeFieldEnd()
    if self.vote_count is not None:
      oprot.writeFieldBegin('vote_count', TType.I32, 25)
      oprot.writeI32(self.vote_count)
      oprot.writeFieldEnd()
    oprot.writeFieldStop()
    oprot.writeStructEnd()

  def validate(self):
    return


  def __hash__(self):
    value = 17
    value = (value * 31) ^ hash(self.adult)
    value = (value * 31) ^ hash(self.backdrop_path)
    value = (value * 31) ^ hash(self.belongs_to_collection)
    value = (value * 31) ^ hash(self.budget)
    value = (value * 31) ^ hash(self.genres)
    value = (value * 31) ^ hash(self.homepage)
    value = (value * 31) ^ hash(self.id)
    value = (value * 31) ^ hash(self.imdb_id)
    value = (value * 31) ^ hash(self.original_language)
    value = (value * 31) ^ hash(self.original_title)
    value = (value * 31) ^ hash(self.overview)
    value = (value * 31) ^ hash(self.popularity)
    value = (value * 31) ^ hash(self.poster_path)
    value = (value * 31) ^ hash(self.production_companies)
    value = (value * 31) ^ hash(self.production_countries)
    value = (value * 31) ^ hash(self.release_date)
    value = (value * 31) ^ hash(self.revenue)
    value = (value * 31) ^ hash(self.runtime)
    value = (value * 31) ^ hash(self.spoken_languages)
    value = (value * 31) ^ hash(self.status)
    value = (value * 31) ^ hash(self.tagline)
    value = (value * 31) ^ hash(self.title)
    value = (value * 31) ^ hash(self.video)
    value = (value * 31) ^ hash(self.vote_average)
    value = (value * 31) ^ hash(self.vote_count)
    return value

  def __repr__(self):
    L = ['%s=%r' % (key, value)
      for key, value in self.__dict__.iteritems()]
    return '%s(%s)' % (self.__class__.__name__, ', '.join(L))

  def __eq__(self, other):
    return isinstance(other, self.__class__) and self.__dict__ == other.__dict__

  def __ne__(self, other):
    return not (self == other)

class Movies:
  """
  Attributes:
   - movies
  """

  thrift_spec = (
    None, # 0
    (1, TType.LIST, 'movies', (TType.STRUCT,(Movie, Movie.thrift_spec)), None, ), # 1
  )

  def __init__(self, movies=None,):
    self.movies = movies

  def read(self, iprot):
    if iprot.__class__ == TBinaryProtocol.TBinaryProtocolAccelerated and isinstance(iprot.trans, TTransport.CReadableTransport) and self.thrift_spec is not None and fastbinary is not None:
      fastbinary.decode_binary(self, iprot.trans, (self.__class__, self.thrift_spec))
      return
    iprot.readStructBegin()
    while True:
      (fname, ftype, fid) = iprot.readFieldBegin()
      if ftype == TType.STOP:
        break
      if fid == 1:
        if ftype == TType.LIST:
          self.movies = []
          (_etype31, _size28) = iprot.readListBegin()
          for _i32 in xrange(_size28):
            _elem33 = Movie()
            _elem33.read(iprot)
            self.movies.append(_elem33)
          iprot.readListEnd()
        else:
          iprot.skip(ftype)
      else:
        iprot.skip(ftype)
      iprot.readFieldEnd()
    iprot.readStructEnd()

  def write(self, oprot):
    if oprot.__class__ == TBinaryProtocol.TBinaryProtocolAccelerated and self.thrift_spec is not None and fastbinary is not None:
      oprot.trans.write(fastbinary.encode_binary(self, (self.__class__, self.thrift_spec)))
      return
    oprot.writeStructBegin('Movies')
    if self.movies is not None:
      oprot.writeFieldBegin('movies', TType.LIST, 1)
      oprot.writeListBegin(TType.STRUCT, len(self.movies))
      for iter34 in self.movies:
        iter34.write(oprot)
      oprot.writeListEnd()
      oprot.writeFieldEnd()
    oprot.writeFieldStop()
    oprot.writeStructEnd()

  def validate(self):
    return


  def __hash__(self):
    value = 17
    value = (value * 31) ^ hash(self.movies)
    return value

  def __repr__(self):
    L = ['%s=%r' % (key, value)
      for key, value in self.__dict__.iteritems()]
    return '%s(%s)' % (self.__class__.__name__, ', '.join(L))

  def __eq__(self, other):
    return isinstance(other, self.__class__) and self.__dict__ == other.__dict__

  def __ne__(self, other):
    return not (self == other)
