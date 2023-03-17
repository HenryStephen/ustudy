package cn.edu.nciae.ustudy.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author YeLi
 * @date 2020-09-06
 */
public class User extends Model<User> {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/**
	 * 头像链接
	 */
	private String profilePic;

	/**
	 * 密码
	 */
	@JsonIgnore
	private String password;

	/**
	 * 昵称
	 */
	private String username;

	/**
	 * 真实名字
	 */
	private String realName;

	/**
	 * 生日
	 */
	private LocalDate birthday;

	/**
	 * 城市
	 */
	private String city;

	/**
	 * 手机号
	 */
	private String telephone;

	@TableField("is_deleted")
	@TableLogic
	private Boolean deleted;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", profilePic='" + profilePic + '\'' +
				", password='" + password + '\'' +
				", username='" + username + '\'' +
				", realName='" + realName + '\'' +
				", birthday=" + birthday +
				", city='" + city + '\'' +
				", telephone='" + telephone + '\'' +
				", deleted=" + deleted +
				'}';
	}
}
