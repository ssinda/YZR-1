package net.nigne.yzrproject.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="purchase_list")
public class PurchaseVO {
   @Id
   @GeneratedValue(generator="NoSequence", strategy=GenerationType.SEQUENCE)
   @SequenceGenerator(name="NoSequence", sequenceName="PURCHASE_NO_SEQ", allocationSize=1)
   @Column(name="no")
   int no;
   
   @Column(name="member_id")
   String member_id;
   
   @Column(name="product_name")
   String product_name;
   
   @Column(name="product_price")
   String product_price;
   
   public int getNo() {
      return no;
   }

   public void setNo(int no) {
      this.no = no;
   }

   public String getMember_id() {
      return member_id;
   }

   public void setMember_id(String member_id) {
      this.member_id = member_id;
   }

   public String getProduct_name() {
      return product_name;
   }

   public void setProduct_name(String product_name) {
      this.product_name = product_name;
   }
   
   public String getProduct_price() {
      return product_price;
   }

   public void setProduct_price(String product_price) {
      this.product_price = product_price;
   }

}