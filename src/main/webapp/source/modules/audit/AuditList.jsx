import React from 'react';
import {connect} from 'react-redux';
import {Icon, Table, Row, Col, Timeline} from 'antd';
import moment from 'moment';
// import {refreshSpecialItemList, changeSpecialItemStatus} from 'actions/SpecialItemActions';
/**
 * SpecialItem: zyj
 * Date: 16/7/19.
 * Time: 上午10:26.
 */
class AuditList extends React.Component {

    constructor(props) {
        super(props);
    }

    render() {
        const redspan = {color: "red"};
        const {auditList=[]} = this.props;
        // const specialItemList = [{"name":"aaaa1","code":"凭证1","expiryDate":"凭证fff","auditOrg":"凭证ffrr","file":'[{"uid":"7051ce422c9f2eabe963163d4b73a276128a991b","url":"http://localhost:8080/incc/file/get/1/4/7051ce422c9f2eabe963163d4b73a276128a991b.jpg","ext":"jpg","name":"1.pic.jpg","status":"done"},{"uid":"2990cda696b888b39a2fb42b5c149a7d67e7b90b","url":"http://localhost:8080/incc/file/get/1/4/2990cda696b888b39a2fb42b5c149a7d67e7b90b.jpg","ext":"jpg","name":"07090518-029ff26fac014d72a7786937e8319c78.jpg","status":"done"}]'},{"name":"aaaa2","code":"凭证2","expiryDate":"凭证fff","auditOrg":"凭证ffrr","file":""}]
        let rows = [];
                {
                    if(auditList)auditList.map((item, index)=>{
                        switch(item.step){
                            case 1:
                                rows.push(
                                    <Timeline.Item key={index}>
                                        <Row><Col span={24}><b>申请提交成功</b></Col></Row>
                                        <Row><Col span={12}>标识申请代理人:{item.creatorPo.name}</Col><Col span={12}>申请时间:{moment(item.auditDate).format('ll')}</Col></Row>
                                        <Row><Col span={12}>联系电话:{item.creatorPo.phone}</Col><Col span={12}>标识申请机构名称:{item.creatorPo.distributor.name}</Col></Row>
                                        {item.result==0 ?<Row><Col span={24} style={redspan}>审核不通过原因:{item.reason}</Col></Row>:""}
                                    </Timeline.Item>
                                );
                                break;
                            case 2:
                                rows.push(
                                    <Timeline.Item key={index}>
                                        <Row><Col span={24}><b>审核{item.result==0 ? "未":""}通过</b></Col></Row>
                                        <Row><Col span={12}>审核人:{item.creatorPo.name}</Col><Col span={12}>审核时间:{moment(item.auditDate).format('ll')}</Col></Row>
                                        <Row><Col span={12}>联系电话:{item.creatorPo.phone}</Col><Col span={12}>审核机构名称:{item.creatorPo.distributor.name}</Col></Row>
                                        {item.result==0 ?<Row><Col span={24} style={redspan}>审核不通过原因:{item.reason}</Col></Row>:""}
                                    </Timeline.Item>
                                );
                                break;
                           case 3:
                                rows.push(
                                    <Timeline.Item key={index}>
                                        <Row><Col span={24}><b>财务审核{item.result==0 ? "未":""}通过</b></Col></Row>
                                        <Row><Col span={12}>财务审核人:{item.creatorPo.name}</Col><Col span={12}>收费时间:{moment(item.auditDate).format('ll')}</Col></Row>
                                        <Row><Col span={12}>联系电话:{item.creatorPo.phone}</Col><Col span={12}>已收取标识费用:{item.fee}</Col></Row>
                                        {item.result==0 ?<Row><Col span={24} style={redspan}>审核不通过原因:{item.reason}</Col></Row>:""}
                                    </Timeline.Item>
                                );
                                break;
                           case 4:
                                rows.push(
                                    <Timeline.Item key={index}>
                                        <Row><Col span={24}><b>复核{item.result==0 ? "未":""}通过</b></Col></Row>
                                        <Row><Col span={12}>复核人:{item.creatorPo.name}</Col><Col span={12}>复核时间:{moment(item.auditDate).format('ll')}</Col></Row>
                                        <Row><Col span={12}>联系电话:{item.creatorPo.phone}</Col><Col span={12}>审核机构名称:{item.creatorPo.distributor.name}</Col></Row>
                                        {item.result==0 ?<Row><Col span={24} style={redspan}>审核不通过原因:{item.reason}</Col></Row>:""}
                                    </Timeline.Item>
                                );
                                break;
                           case 5:
                                rows.push(
                                    <Timeline.Item key={index}>
                                        <Row><Col span={24}><b>终审{item.result==0 ? "未":""}通过</b></Col></Row>
                                        <Row><Col span={12}>终审人:{item.creatorPo.name}</Col><Col span={12}>终审时间:{moment(item.auditDate).format('ll')}</Col></Row>
                                        <Row><Col span={12}>联系电话:{item.creatorPo.phone}</Col><Col span={12}>审核机构名称:{item.creatorPo.distributor.name}</Col></Row>
                                        {item.result==0 ?<Row><Col span={24} style={redspan}>审核不通过原因:{item.reason}</Col></Row>:""}
                                    </Timeline.Item>
                                );
                                break;

                        }
                    }
                    )
                }
        return (
            <Timeline>
                {rows}
            </Timeline>
        )
    }
};
export default connect(
    state => {
        let {auditList} = state;
        return {
            auditList
        }
    }
)(AuditList);
