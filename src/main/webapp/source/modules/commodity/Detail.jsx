import React from "react";
import {connect} from 'react-redux';

import SubPage from 'modules/common/SubPage';
import CommodityDetail from 'modules/commodity/CommodityDetail';
import {getCommodity} from "actions/CommodityActions";
import {findByCommodityId} from "actions/AuditActions";
import moment from 'moment';
import {Form, Input, Row, Col, Img} from "antd";
const FormItem = Form.Item;
const createForm = Form.create;

function hasRoles(currentUser,requiredRoles) {
    if(!currentUser.roles)return false;
    let roleIds = currentUser.roles.map(item=> item.id);
    return roleIds.find((n) => requiredRoles.includes(n))
}

class Detail extends React.Component {

    constructor(props) {
        super(props);
    }

    onChange = (e) => {
        this.setState({
            result: e.target.value,
        });
    }

    componentDidMount() {
        this.props.dispatch(getCommodity(this.props.params.id))
        this.props.dispatch(findByCommodityId(this.props.params.id))
    }


    render() {
        const {getFieldDecorator} = this.props.form;
        const {selectedCommodity} = this.props;
        const {currentUser} = this.props;
        const {auditList} = this.props;
        const lastAutit = auditList[auditList.length-1];
        const {auditDate="", beginDate="",expireDate=""}=lastAutit?lastAutit:{};
        console.log("++++++++", lastAutit)
        let hasRole = false;
        if (currentUser && currentUser.roles) {
            // console.log("++++++++++++++",currentUser)
            hasRole = hasRoles(currentUser, ['ROLE_ADMIN','ROLE_FINAL_AUDITOR']);
    }
        return (
            <SubPage breadcrumb="/后台管理/审核管理/商品详情">

            <CommodityDetail />
            {selectedCommodity.status == 5 && hasRole?
                <div>
                <Row>
                    <Col span={6}>
                        二维码标码：
                    </Col>
                    <Col span={18}>
                        <img width="200px" height="60" src={'/incc/qrcode/' + selectedCommodity.id}/>
                    </Col>
                </Row>
                <Row>
                    <Col span={6}>
                        授予标识时间：
                    </Col>
                    <Col span={6}>
                        {moment(auditDate).format('ll ')}
                    </Col>
                    <Col span={6}>
                        标识有效期：
                    </Col>
                    <Col span={6}>
                        {moment(beginDate).format('ll')} 至 {moment(expireDate).format('ll')}
                    </Col>
                </Row>
                <Row>
                    <Col span={6}>
                        授予标识原版：
                    </Col>
                    <Col span={6}>
                        <a href={'/incc/qrcode/' + selectedCommodity.id}>下载</a>
                    </Col>
                    <Col span={6}>
                        授权书下载：
                    </Col>
                    <Col span={6}>
                        <a href={'/incc/certification/' + selectedCommodity.id}>下载</a>
                    </Col>
                </Row>
                <Row>
                    <Col>
                        &nbsp;
                    </Col>
                </Row>
                <Row>
                    <Col>
                        &nbsp;
                    </Col>
                </Row>
                </div>:<div></div>
                }

            </SubPage>
        )
    }
}
;
Detail = createForm()(Detail);
export default  connect(
    state => {
        let {selectedCommodity, auditList, currentUser} = state;
        return {
            selectedCommodity, auditList, currentUser
        }
    }
)(Detail);