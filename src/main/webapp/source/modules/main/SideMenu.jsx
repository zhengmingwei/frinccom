import React from 'react';
import ReactDOM from 'react-dom';

import {Menu, Icon} from 'antd';
const SubMenu = Menu.SubMenu;
import {Link} from 'react-router';

function hasRoles(currentUser,requiredRoles) {
    if(!currentUser.roles)return false;
    let roleIds = currentUser.roles.map(item=> item.id);
    return roleIds.find((n) => requiredRoles.includes(n))
}

/**
 * User: zyj
 * Date: 16/7/19.
 * Time: 上午11:46.
 */
export default class SideMenu extends React.Component {
    constructor(props) {
        super(props);
    }


    render() {
        // console.log("this.props",this.props);
        const {currentUser} = this.props;
        const {location} = this.props;
        const pathItems = location.pathname.length > 1 ? location.pathname.trim().match(/\/\w+/g) : ["", ""];
        return (
            <Menu mode="inline" defaultOpenKeys={[pathItems[0]]} selectedKeys={[pathItems[1]]} >

                {hasRoles(currentUser,['ROLE_CUSTOMER'])?

                    <SubMenu key="sub1" title={<span><Icon type="user" />认证管理中心</span>}>
                        <Menu.Item key="1">
                            <Link to="/manager/commodity/form">发布认证信息</Link>
                        </Menu.Item>
                        <Menu.Item key="2">
                            <Link to="/manager/commodity/list/1">已发布认证信息</Link>
                        </Menu.Item>
                        <Menu.Item key="3">
                            <Link to="/manager/commodity/list/2">待审核的认证信息</Link>
                        </Menu.Item>
                        <Menu.Item key="4">
                            <Link to="/manager/commodity/list/3">审核中的认证信息</Link>
                        </Menu.Item>
                        <Menu.Item key="5">
                            <Link to="/manager/commodity/list/4">已审核通过认证信息</Link>
                        </Menu.Item>
                        <Menu.Item key="6">
                            <Link to="/manager/commodity/list/5">已审核未通过认证信息</Link>
                        </Menu.Item>
                        <Menu.Item key="29">
                            <Link to="/manager/orderPriceSystem/queryBuy">订购套餐</Link>
                        </Menu.Item>
                        <Menu.Item key="30">
                            <Link to="/alipay/orderPackagesByUserId2">我的套餐</Link>
                        </Menu.Item>
                    </SubMenu>:''
                }

                {hasRoles(currentUser,['ROLE_AUDITOR'])?

                    <SubMenu key="sub2" title={<span><Icon type="user" />审核管理中心</span>}>
                        <Menu.Item key="7">
                            <Link to="/manager/commodity/list/12">待审核的认证信息</Link>
                        </Menu.Item>
                        <Menu.Item key="8">
                            <Link to="/manager/commodity/list/6">已审核通过认证信息</Link>
                        </Menu.Item>
                        <Menu.Item key="9">
                            <Link to="/manager/commodity/list/7">已审核未通过认证信息</Link>
                        </Menu.Item>
                        <Menu.Item key="31">
                            <Link to="/manager/user/list2">分销商用户管理</Link>
                        </Menu.Item>
                    </SubMenu>:''
                }
                {hasRoles(currentUser, ['ROLE_FEE_AUDITOR']) ?
                    <SubMenu key="sub3" title={<span><Icon type="user"/>标识费用审核管理</span>}>
                        <Menu.Item key="10">
                            <Link to="/manager/payment/form">登记标识费用</Link>
                        </Menu.Item>
                        <Menu.Item key="11">
                            <Link to="/manager/payment/list">标识费用列表</Link>
                        </Menu.Item>
                        {/*<Menu.Item key="/commodity11">*/}
                        {/*<Link to="/manager/commodity/list">标识费用统计查询</Link>*/}
                        {/*</Menu.Item>*/}
                    </SubMenu> : ''
                }
                {hasRoles(currentUser, ['ROLE_REAUDITOR']) ?
                    <SubMenu key="sub4" title={<span><Icon type="user"/>复核管理中心</span>}>
                        <Menu.Item key="12">
                            <Link to="/manager/commodity/list/8">待复核的认证信息</Link>
                        </Menu.Item>
                        <Menu.Item key="13">
                            <Link to="/manager/commodity/list/9">已复核通过认证信息</Link>
                        </Menu.Item>
                        <Menu.Item key="14">
                            <Link to="/manager/commodity/list/10">已复核未通过认证信息</Link>
                        </Menu.Item>
                    </SubMenu> : ''
                }
                {hasRoles(currentUser, ['ROLE_FINAL_AUDITOR']) ?
                    <SubMenu key="sub5" title={<span><Icon type="user"/>终审管理中心</span>}>
                        <Menu.Item key="15">
                            <Link to="/manager/commodity/list/13">待终审的认证信息</Link>
                        </Menu.Item>
                        <Menu.Item key="16">
                            <Link to="/manager/commodity/list/14">已终审通过认证</Link>
                        </Menu.Item>
                        <Menu.Item key="17">
                            <Link to="/manager/commodity/list/11">已终审未通过认证信息</Link>
                        </Menu.Item>
                    </SubMenu> : ''
                }
                {hasRoles(currentUser,['ROLE_AUDITOR','ROLE_FINAL_AUDITOR','ROLE_ADMIN'])?

                    <SubMenu key="sub6" title={<span><Icon type="user" />标识到期提醒</span>}>
                        <Menu.Item key="18">
                            <Link to="/manager/precautionary/list/0">所有授权的企业</Link>
                        </Menu.Item>
                        <Menu.Item key="19">
                            <Link to="/manager/precautionary/list/1">标识即将到期的企业</Link>
                        </Menu.Item>
                        <Menu.Item key="20">
                            <Link to="/manager/precautionary/list/2">标识已过期的企业</Link>
                        </Menu.Item>
                    </SubMenu>:''
                }
                {/*<SubMenu key="sub6" title={<span><Icon type="user" />标识到期提醒(分销商)</span>}>*/}
                    {/*<Menu.Item key="18">*/}
                        {/*<Link to="/manager/commodity/list">所有授权的企业</Link>*/}
                    {/*</Menu.Item>*/}
                    {/*<Menu.Item key="19">*/}
                        {/*<Link to="/manager/commodity/list">标识即将到期的企业</Link>*/}
                    {/*</Menu.Item>*/}
                    {/*<Menu.Item key="19">*/}
                        {/*<Link to="/manager/commodity/list">标识已过期的企业</Link>*/}
                    {/*</Menu.Item>*/}
                {/*</SubMenu>*/}
                {/*<SubMenu key="sub7" title={<span><Icon type="user" />标识到期提醒(总部)</span>}>*/}
                    {/*<Menu.Item key="20">*/}
                        {/*<Link to="/manager/commodity/list">所有授权的企业</Link>*/}
                    {/*</Menu.Item>*/}
                    {/*<Menu.Item key="21">*/}
                        {/*<Link to="/manager/commodity/list">标识即将到期的企业</Link>*/}
                    {/*</Menu.Item>*/}
                    {/*<Menu.Item key="22">*/}
                        {/*<Link to="/manager/commodity/list">标识已过期的企业</Link>*/}
                    {/*</Menu.Item>*/}
                {/*</SubMenu>*/}

                {hasRoles(currentUser, ['ROLE_ADMIN']) ?
                    <SubMenu key="sub8" title={<span><Icon type="user"/>系统管理</span>}>
                        <Menu.Item key="23">
                            <Link to="/manager/distributor/form">添加分销商</Link>
                        </Menu.Item>
                        <Menu.Item key="24">
                            <Link to="/manager/distributor/list">分销商企业名录管理</Link>
                        </Menu.Item>
                        <Menu.Item key="25">
                            <Link to="/manager/user/form">添加用户</Link>
                        </Menu.Item>
                        <Menu.Item key="26">
                            <Link to="/manager/user/list">用户管理</Link>
                        </Menu.Item>
                        <Menu.Item key="27">
                            <Link to="/manager/dictionary/list">类别管理</Link>
                        </Menu.Item>
                        <Menu.Item key="28">
                            <Link to="/manager/orderPriceSystem/query2">价格体系管理</Link>
                        </Menu.Item> 
                    </SubMenu> : ''
                }
            </Menu>
        )
    }
};