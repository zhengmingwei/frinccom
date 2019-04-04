import React from "react";

import {connect} from "react-redux";
import {Button, Col, Form, Row} from "antd";
import {showModalDialog} from "actions/CommonAction";
import {getCommodity} from "actions/CommodityActions";
import SubPage from "modules/common/SubPage";
import PicView from "modules/common/PicView";
import SpecialItemList from "modules/commodity/SpecialItemList";
import OtherQualificationList from "modules/commodity/OtherQualificationList";
import AuditList from "modules/audit/AuditList";
const FormItem = Form.Item;
class CommodityForm extends React.Component {

    constructor(props) {
        super(props);
    }

    componentDidMount() {
        // let id = this.props.id;
        //
        // this.props.dispatch(getCommodity(id));
        if(this.props.params){
            this.props.dispatch(getCommodity(id));
        }
    }

    render() {
        const {selectedCommodity:{name, categoryPo, industryPo, pic, company, factory, brand}} = this.props;
        const formItemLayout = {
            labelCol: {
                xs: {span: 24},
                sm: {span: 3},
            },
            wrapperCol: {
                xs: {span: 24},
                sm: {span: 12},
            },
        };
        const picLayout = {
            labelCol: {
                xs: {span: 24},
                sm: {span: 9},
            },
            wrapperCol: {
                xs: {span: 24},
                sm: {span: 12},
            },
        };

        const doubleFormItemLayout = {
            labelCol: {
                xs: {span: 12},
                sm: {span: 6},
            },
            wrapperCol: {
                xs: {span: 12},
                sm: {span: 12},
            }
        };
        const tailFormItemLayout = {
            wrapperCol: {
                xs: {
                    span: 24,
                    offset: 0,
                },
                sm: {
                    span: 14,
                    offset: 6,
                },
            },
        };


        return (
            <div>
                {/*<Form>*/}

                    <section className="markdown">
                        <h3>商品基本信息</h3>
                    </section>
                    <hr/>
                    <br/>
                    <div >
                        {/*<section><h1>发布企业认证信息</h1></section>*/}


                        <FormItem
                            {...formItemLayout}
                            label={"所属行业:"}
                        >
                            {industryPo ? industryPo.value:""}
                        </FormItem>
                        <FormItem
                            {...formItemLayout}
                            label="商品名称"
                        >
                            {name}
                        </FormItem>
                        <FormItem
                            {...formItemLayout}
                            label={"所属类别"}
                        >
                            {categoryPo ?  categoryPo.value: ""}
                        </FormItem>
                        <FormItem
                            {...formItemLayout}
                            label="商品图片"
                        >
                            <PicView fileInfo={pic}/>
                        </FormItem>
                        <Row gutter={40} style={{marginBottom: "12px"}}><Col span={24}>
                        <SpecialItemList {...this.props} editAble={false}/>
                        </Col></Row>
                    </div>
                    {/*brand begin*/}
                    <section className="markdown">
                        <h3>品牌信息</h3>
                    </section>
                    <hr/>
                    <br/>
                    <div style={{backgroundColor: "#f5f5f5"}}>
                        <br/>
                    <Row><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label="品牌名称"

                        >
                            {brand ? brand.name: ""}
                        </FormItem>
                    </Col><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label="品牌注册号"
                        >
                            {brand ? brand.code: ""}
                        </FormItem>
                    </Col><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label="品牌权利人"
                        >
                            {brand ? brand.owner: ""}
                        </FormItem>
                    </Col><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label={"品牌状态"}
                        >
                            {brand ? brand.brandStatusValue: ""}
                        </FormItem>
                    </Col></Row>
                    <Row><Col span={8}>
                        <FormItem
                            {...picLayout}
                            label="商标注册证"
                        >
                            <PicView fileInfo={brand ? brand.registrationCertificate: ""}/>
                        </FormItem>
                    </Col>
                        <Col span={8}>
                            <FormItem
                                {...picLayout}
                                label="受理通知书"
                            >
                                <PicView fileInfo={brand ? brand.notification: ""}/>
                            </FormItem>
                        </Col>
                        <Col span={8}>
                            <FormItem
                                {...picLayout}
                                label="授权书扫描件"
                            >
                                <PicView fileInfo={brand ? brand.authorization: ""}/>
                            </FormItem>
                        </Col>
                    </Row>
                    </div>
                    {/*Brand end*/}
                    <section className="markdown">
                        <h3>商品其他资质</h3>
                    </section>
                    <hr/>
                    <br/>
                    <OtherQualificationList {...this.props} editAble={false}/>
                    {/*经营企业基本信息 begin*/}
                    <section className="markdown">
                        <h3>经营企业基本信息</h3>
                    </section>
                    <hr/>
                    <br/>
                    <div style={{backgroundColor: "#f5f5f5"}}>
                        <br/>
                    <FormItem
                        {...formItemLayout}
                        label="企业名称"
                    >
                        {company ? company.name: ""}
                    </FormItem>
                    <Row><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label="企业身份代码类型"

                        >
                            {company ? company.idTypeValue: ""}
                        </FormItem>
                    </Col><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label="身份代码"
                        >
                            {company ? company.idCode: ""}
                        </FormItem>
                    </Col><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label="企业类型"
                        >
                            {company ? company.companyTypeValue: ""}
                        </FormItem>
                    </Col><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label={"法定代表人"}
                        >
                            {company ? company.legalPerson: ""}
                        </FormItem>
                    </Col></Row>
                    <FormItem
                        {...formItemLayout}
                        label="企业注册地址"
                    >
                        {company ? company.regAddr: ""}
                    </FormItem>
                    <FormItem
                        {...formItemLayout}
                        label="企业经营地址"
                    >
                        {company ? company.businessAddr: ""}
                    </FormItem>
                    <Row><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label="营业期限自"

                        >
                            {company ? company.businessBegin: ""}
                        </FormItem>
                    </Col><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label="营业期限至"
                        >
                            {company ? company.businessEnd: ""}
                        </FormItem>
                    </Col><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label="联系人"
                        >
                            {company ? company.contacter: ""}
                        </FormItem>
                    </Col><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label={"联系电话"}
                        >
                            {company ? company.phone: ""}
                        </FormItem>
                    </Col><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label="电子邮件"
                        >
                            {company ? company.mail: ""}
                        </FormItem>
                    </Col><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label={"移动电话"}
                        >
                            {company ? company.mphone: ""}
                        </FormItem>
                    </Col></Row>
                    <FormItem
                        {...formItemLayout}
                        label={"经营范围"}
                    >
                        {company ? company.businessScope: ""}
                    </FormItem>
                    <FormItem
                        {...formItemLayout}
                        label="营业执照副本扫描件"
                    >
                        <PicView fileInfo={company ? company.businessLicense: ""}/>
                    </FormItem>
                    <FormItem
                        {...formItemLayout}
                        labelCol={{sm: {span: 5}}}
                        label={"其他资质"}
                    >
                        {company ? company.qese: ""}
                    </FormItem>
                     <FormItem
                        {...formItemLayout}
                        labelCol={{sm: {span: 5}}}
                        label={"资质编号"}
                    >
                        {company ? company.qeseCode: ""}
                    </FormItem>
                    <FormItem
                        {...formItemLayout} labelCol={{sm: {span: 6}}}
                        label="其他资质扫描件"
                    >
                        <PicView fileInfo={company ? company.qeseFile: ""}/>
                    </FormItem>
                    </div>
                    {/*经营企业基本信息*/}
                    {/*生产企业基本信息 begin*/}
                    <section className="markdown">
                        <h3>生产企业基本信息</h3>
                    </section>
                    <hr/>
                    <br/>
                    <FormItem
                        {...formItemLayout}
                        label="企业名称"
                    >
                        {factory ? factory.name: ""}
                    </FormItem>
                    <Row><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label="企业身份代码类型"

                        >
                            {factory ? factory.idTypeValue: ""}
                        </FormItem>
                    </Col><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label="身份代码"
                        >
                            {factory ? factory.idCode: ""}
                        </FormItem>
                    </Col><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label="企业类型"
                        >
                            {factory ? factory.companyTypeValue: ""}
                        </FormItem>
                    </Col><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label={"法定代表人"}
                        >
                            {factory ? factory.legalPerson: ""}
                        </FormItem>
                    </Col></Row>
                    <FormItem
                        {...formItemLayout}
                        label="企业住所"
                    >
                        {factory ? factory.addr: ""}
                    </FormItem>

                    <Row><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label="营业期限自"

                        >
                            {factory ? factory.businessBegin: ""}
                        </FormItem>
                    </Col><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label="营业期限至"
                        >
                            {factory ? factory.businessEnd: ""}
                        </FormItem>
                    </Col><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label="联系人"
                        >
                            {factory ? factory.contacter: ""}
                        </FormItem>
                    </Col><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label={"联系电话"}
                        >
                            {factory ? factory.phone: ""}
                        </FormItem>
                    </Col><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label="电子邮件"
                        >
                            {factory ? factory.mail: ""}
                        </FormItem>
                    </Col><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label={"移动电话"}
                        >
                            {factory ? factory.mphone: ""}
                        </FormItem>
                    </Col></Row>
                    <FormItem
                        {...formItemLayout}
                        label={"经营范围"}
                    >
                        {factory ? factory.businessScope: ""}
                    </FormItem>
                    <FormItem
                        {...formItemLayout}
                        label="营业执照副本扫描件"
                    >
                        <PicView fileInfo={factory ? factory.businessLicense: ""}/>
                    </FormItem>
                    <FormItem
                        {...formItemLayout}
                        labelCol={{sm: {span: 5}}}
                        label={"其他资质"}
                    >
                        {factory ? factory.qese: ""}
                    </FormItem>
                    <FormItem
                        {...formItemLayout}
                        labelCol={{sm: {span: 5}}}
                        label={"资质编码"}
                    >
                        {factory ? factory.qeseCode: ""}
                    </FormItem>
                    <FormItem
                        {...formItemLayout} labelCol={{sm: {span: 6}}}
                        label="其他资质扫描件"
                    >
                        <PicView fileInfo={factory ? factory.qeseFile: ""}/>
                    </FormItem>
                    {/*生产企业基本信息*/}

                {/*</Form>*/}
<AuditList />

        </div>

        )
    }
}
;

export default connect(
    state => {
        let {selectedCommodity} = state;
        return {
            selectedCommodity
        }
    }
)(CommodityForm);