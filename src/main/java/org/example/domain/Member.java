package org.example.domain;

import jdk.jfr.Name;
import org.example.domain.embeddable.Address;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
/*@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ",
        allocationSize = 1,
        initialValue = 1
)*/
@NamedQueries({
        @NamedQuery(name = "Member.findByName",
                query = "select m from Member m where m.name = :name"),
        @NamedQuery(name = "Member.count",
                query = "select count(m) from Member m")
})
public class Member {

    @Id
    @GeneratedValue(generator = "MEMBER_SEQ_GENERATOR")
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    @Embedded
    Address address;

    /**
     * 값타입 컬렉션은 주로 체크박스에서 값을 추적할 필요도 없을 때 사용.
     * 주소는 주소만 추적해서 가져올 수도 있끼 대문에 값타입으로 사용해서는 안된다
     */
    @ElementCollection
    @CollectionTable(name = "FAVORITE_FOODS",
            joinColumns = @JoinColumn(name = "MEMBER_ID"))
    @Column(name = "FOOD_NAME") //primitive인경우 예외적으로 column지정 가능.
            /**
             * MEMBER_ID를 FK로 쓴다. 컬렉션을 데이터베이스에서 쓰기 위해 별도의 매핑 테이블이 필요하다.
             */
            Set<String> favoriteFoods = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "MEMBER_ID")
    List<AddressEntity> addressHistory = new ArrayList<>();

   /* @ManyToMany
    @JoinTable(name = "MEMBER_PRODUCT",
            joinColumns = @JoinColumn(name = "MEMBER_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
    private List<Product> products = new ArrayList<>();*/

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        orders.add(order);
        order.setMember(this);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Locker getLocker() {
        return locker;
    }

    public void setLocker(Locker locker) {
        this.locker = locker;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
        if (!team.getMembers().contains(this)) {
            team.getMembers().add(this);
        }
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }

    public List<AddressEntity> getAddressHistory() {
        return addressHistory;
    }

    public void setAddressHistory(List<AddressEntity> addressHistory) {
        this.addressHistory = addressHistory;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", team=" + team.getName() +
                ", locker=" + locker.getName() +
                ", address=" + address +
                ", favoriteFoods=" + favoriteFoods +
                ", addressHistory=" + addressHistory +
                ", orders=" + orders +
                '}';
    }

    /**
     * SELECT m.member_id, m.name, m.team_id, t.name as team_name: 이 부분은 검색 결과에 포함할 열을 지정합니다.
     * member 테이블의 member_id, name, team_id 열과 team 테이블의 name 열을 선택하며, team 테이블의 name 열은 team_name으로
     * 별칭을 붙여 선택합니다.
     */
}
