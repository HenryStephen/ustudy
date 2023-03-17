<template >
    <Menu :active-name="activeName" id="collection-menu" style="width: 240px;">
        <MenuGroup  id="menu">
            <div id="titel">个人中心</div>
            <MenuItem name="1" to='/myCourse'>
                我的课程
            </MenuItem>
            <MenuItem name="2" to='/collection'>
                收藏的课程
            </MenuItem>
            <MenuItem name="3" to='/myOrder'>
                我的订单
            </MenuItem>
            <MenuItem name="4">
                我的优惠券
            </MenuItem>
            <MenuItem name="5">
                我的测评
            </MenuItem>
            <MenuItem name="6">
                帐号设置
            </MenuItem>
        </MenuGroup>
    </Menu>
</template>

<script>
export default {
    name: 'CollectionMenu',
    props: ['activeName']
}
</script>

<style>
#menu {
    text-align:left;
}
#collection-menu {
    float: left;
    position: relative;
    left: 150px;
    top: 1px;
    border: none;
}
#titel {
    background-color:coral;
    width: 240px;
    height: 50px;
    color:white;
    font-size: 20px;
    padding-top: 10px;
    padding-left: 34px;
}
</style>
