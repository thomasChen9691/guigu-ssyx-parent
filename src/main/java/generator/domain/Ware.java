package generator.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

/**
* 仓库表
* @TableName ware
*/
public class Ware implements Serializable {

    /**
    * id
    */
    @NotNull(message="[id]不能为空")
    @ApiModelProperty("id")
    private Long id;
    /**
    * 名称
    */
    @NotBlank(message="[名称]不能为空")
    @Size(max= 100,message="编码长度不能超过100")
    @ApiModelProperty("名称")
    @Length(max= 100,message="编码长度不能超过100")
    private String name;
    /**
    * 省code
    */
    @Size(max= 20,message="编码长度不能超过20")
    @ApiModelProperty("省code")
    @Length(max= 20,message="编码长度不能超过20")
    private String province;
    /**
    * 城市code
    */
    @Size(max= 20,message="编码长度不能超过20")
    @ApiModelProperty("城市code")
    @Length(max= 20,message="编码长度不能超过20")
    private String city;
    /**
    * 区域code
    */
    @Size(max= 20,message="编码长度不能超过20")
    @ApiModelProperty("区域code")
    @Length(max= 20,message="编码长度不能超过20")
    private String district;
    /**
    * 详细地址
    */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("详细地址")
    @Length(max= 255,message="编码长度不能超过255")
    private String detailAddress;
    /**
    * 经度
    */
    @ApiModelProperty("经度")
    private BigDecimal longitude;
    /**
    * 纬度
    */
    @ApiModelProperty("纬度")
    private BigDecimal latitude;
    /**
    * 创建时间
    */
    @NotNull(message="[创建时间]不能为空")
    @ApiModelProperty("创建时间")
    private Date createTime;
    /**
    * 更新时间
    */
    @NotNull(message="[更新时间]不能为空")
    @ApiModelProperty("更新时间")
    private Date updateTime;
    /**
    * 删除标记（0:不可用 1:可用）
    */
    @NotNull(message="[删除标记（0:不可用 1:可用）]不能为空")
    @ApiModelProperty("删除标记（0:不可用 1:可用）")
    private Integer isDeleted;

    /**
    * id
    */
    private void setId(Long id){
    this.id = id;
    }

    /**
    * 名称
    */
    private void setName(String name){
    this.name = name;
    }

    /**
    * 省code
    */
    private void setProvince(String province){
    this.province = province;
    }

    /**
    * 城市code
    */
    private void setCity(String city){
    this.city = city;
    }

    /**
    * 区域code
    */
    private void setDistrict(String district){
    this.district = district;
    }

    /**
    * 详细地址
    */
    private void setDetailAddress(String detailAddress){
    this.detailAddress = detailAddress;
    }

    /**
    * 经度
    */
    private void setLongitude(BigDecimal longitude){
    this.longitude = longitude;
    }

    /**
    * 纬度
    */
    private void setLatitude(BigDecimal latitude){
    this.latitude = latitude;
    }

    /**
    * 创建时间
    */
    private void setCreateTime(Date createTime){
    this.createTime = createTime;
    }

    /**
    * 更新时间
    */
    private void setUpdateTime(Date updateTime){
    this.updateTime = updateTime;
    }

    /**
    * 删除标记（0:不可用 1:可用）
    */
    private void setIsDeleted(Integer isDeleted){
    this.isDeleted = isDeleted;
    }


    /**
    * id
    */
    private Long getId(){
    return this.id;
    }

    /**
    * 名称
    */
    private String getName(){
    return this.name;
    }

    /**
    * 省code
    */
    private String getProvince(){
    return this.province;
    }

    /**
    * 城市code
    */
    private String getCity(){
    return this.city;
    }

    /**
    * 区域code
    */
    private String getDistrict(){
    return this.district;
    }

    /**
    * 详细地址
    */
    private String getDetailAddress(){
    return this.detailAddress;
    }

    /**
    * 经度
    */
    private BigDecimal getLongitude(){
    return this.longitude;
    }

    /**
    * 纬度
    */
    private BigDecimal getLatitude(){
    return this.latitude;
    }

    /**
    * 创建时间
    */
    private Date getCreateTime(){
    return this.createTime;
    }

    /**
    * 更新时间
    */
    private Date getUpdateTime(){
    return this.updateTime;
    }

    /**
    * 删除标记（0:不可用 1:可用）
    */
    private Integer getIsDeleted(){
    return this.isDeleted;
    }

}
