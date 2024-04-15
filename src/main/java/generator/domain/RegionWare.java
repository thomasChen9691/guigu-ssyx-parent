package generator.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

/**
* 城市仓库关联表
* @TableName region_ware
*/
public class RegionWare implements Serializable {

    /**
    * id
    */
    @NotNull(message="[id]不能为空")
    @ApiModelProperty("id")
    private Long id;
    /**
    * 开通区域id
    */
    @NotNull(message="[开通区域id]不能为空")
    @ApiModelProperty("开通区域id")
    private Long regionId;
    /**
    * 区域名称
    */
    @NotBlank(message="[区域名称]不能为空")
    @Size(max= 30,message="编码长度不能超过30")
    @ApiModelProperty("区域名称")
    @Length(max= 30,message="编码长度不能超过30")
    private String regionName;
    /**
    * 仓库
    */
    @NotNull(message="[仓库]不能为空")
    @ApiModelProperty("仓库")
    private Long wareId;
    /**
    * 仓库名称
    */
    @NotBlank(message="[仓库名称]不能为空")
    @Size(max= 30,message="编码长度不能超过30")
    @ApiModelProperty("仓库名称")
    @Length(max= 30,message="编码长度不能超过30")
    private String wareName;
    /**
    * 状态（0：未开通 1：已开通）
    */
    @NotNull(message="[状态（0：未开通 1：已开通）]不能为空")
    @ApiModelProperty("状态（0：未开通 1：已开通）")
    private Integer status;
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
    * 开通区域id
    */
    private void setRegionId(Long regionId){
    this.regionId = regionId;
    }

    /**
    * 区域名称
    */
    private void setRegionName(String regionName){
    this.regionName = regionName;
    }

    /**
    * 仓库
    */
    private void setWareId(Long wareId){
    this.wareId = wareId;
    }

    /**
    * 仓库名称
    */
    private void setWareName(String wareName){
    this.wareName = wareName;
    }

    /**
    * 状态（0：未开通 1：已开通）
    */
    private void setStatus(Integer status){
    this.status = status;
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
    * 开通区域id
    */
    private Long getRegionId(){
    return this.regionId;
    }

    /**
    * 区域名称
    */
    private String getRegionName(){
    return this.regionName;
    }

    /**
    * 仓库
    */
    private Long getWareId(){
    return this.wareId;
    }

    /**
    * 仓库名称
    */
    private String getWareName(){
    return this.wareName;
    }

    /**
    * 状态（0：未开通 1：已开通）
    */
    private Integer getStatus(){
    return this.status;
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
