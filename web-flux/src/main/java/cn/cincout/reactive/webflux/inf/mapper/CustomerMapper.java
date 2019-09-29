package cn.cincout.reactive.webflux.inf.mapper;

import cn.cincout.reactive.webflux.domain.Customer;
import cn.cincout.reactive.webflux.domain.CustomerExample;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface CustomerMapper {
    @SelectProvider(type=CustomerSqlProvider.class, method="countByExample")
    long countByExample(CustomerExample example);

    @DeleteProvider(type=CustomerSqlProvider.class, method="deleteByExample")
    int deleteByExample(CustomerExample example);

    @Delete({
        "delete from Customer",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into Customer (name, phoneNumber)",
        "values (#{name,jdbcType=VARCHAR}, #{phonenumber,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Customer record);

    @InsertProvider(type=CustomerSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(Customer record);

    @InsertProvider(type = CustomerSqlProvider.class, method = "insertBatch")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertBatch(@Param("list") List<Customer> customers);

    @SelectProvider(type=CustomerSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="phoneNumber", property="phonenumber", jdbcType=JdbcType.VARCHAR)
    })
    List<Customer> selectByExample(CustomerExample example);

    @Select({
        "select",
        "id, name, phoneNumber",
        "from Customer",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="phoneNumber", property="phonenumber", jdbcType=JdbcType.VARCHAR)
    })
    Customer selectByPrimaryKey(Integer id);

    @UpdateProvider(type=CustomerSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Customer record, @Param("example") CustomerExample example);

    @UpdateProvider(type=CustomerSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Customer record, @Param("example") CustomerExample example);

    @UpdateProvider(type=CustomerSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Customer record);

    @Update({
        "update Customer",
        "set name = #{name,jdbcType=VARCHAR},",
          "phoneNumber = #{phonenumber,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Customer record);
}