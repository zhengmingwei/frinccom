import React from "react";

import {Form, Input, Upload, Button, Icon} from "antd";
import {showModalDialog} from "actions/CommonAction";
import {connect} from 'react-redux';
import {saveDistributor,getDistributor} from "actions/DistributorActions";
import SubPage from 'modules/common/SubPage';
import LimitUpload from 'modules/common/LimitUpload';
import FileList from 'modules/common/FileList';
const FormItem = Form.Item;
const createForm = Form.create;

class DistributorForm extends React.Component {

    constructor(props) {
        super(props);
    }

    componentDidMount() {
        this.props.dispatch(getDistributor(this.props.params.id));
    }



    render() {
        const {getFieldDecorator} = this.props.form;
        const {selectedDistributor:{id, name="", orgCode="", legalPerson="", contact="", phone="", fax="", mail="",province="",address="",businessLicense="[]",orgCodeCertification="[]",taxCertificate="[]"}} = this.props;
        const businessLicenseObj = eval("["+businessLicense+"]");
        const orgCodeCertificationObj =eval ("["+orgCodeCertification+"]");
        const taxCertificateObj = eval("["+taxCertificate+"]");
        console.log(orgCodeCertificationObj);
        const formItemLayout = {
            labelCol: {
                xs: {span: 24},
                sm: {span: 5},
            },
            wrapperCol: {
                xs: {span: 24},
                sm: {span: 12},
            },
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
            <SubPage breadcrumb="/首页/后台管理/普通用户管理">
			<Form>
                {getFieldDecorator('id', {
                    initialValue: id,
                })(
                    <Input type="hidden"/>
                )}
				<FormItem
                    {...formItemLayout}
					label="企业名称"
				>
                    {name}
				</FormItem>
				<FormItem
                    {...formItemLayout}
					label="法人"
				>
                    {legalPerson}
				</FormItem>
				<FormItem
                    {...formItemLayout}
					label="组织机构代码"
				>
                    {orgCode}
				</FormItem>
				<FormItem
                    {...formItemLayout}
					label="联系人"
				>
                    {contact}
				</FormItem>
				<FormItem
                    {...formItemLayout}
					label="电话"
				>
                    {phone}
				</FormItem>
				<FormItem
                    {...formItemLayout}
					label="传真"
				>
                    {fax}
				</FormItem>
                <FormItem
                    {...formItemLayout}
					label="邮箱"
				>
                    {mail}
				</FormItem>
                <FormItem
                    {...formItemLayout}
					label="所在省市"
				>
                    {province}
				</FormItem>
                <FormItem
                    {...formItemLayout}
					label="办公地址"
				>
                    {address}
				</FormItem>
                <FormItem
                    {...formItemLayout}
                    label="营业执照副本"
                >
                    <FileList fileInfoList={businessLicenseObj}/>

                </FormItem>
                <FormItem
                    {...formItemLayout}
                    label="上传组织机构代码"
                >
                    <FileList fileInfoList={orgCodeCertificationObj}/>

                </FormItem>
                <FormItem
                    {...formItemLayout}
                    label="上传税务登记证"
                >
                    <FileList fileInfoList={taxCertificateObj}/>

                </FormItem>
                <FormItem {...tailFormItemLayout}>
                    <Button type="primary" htmlType="button" size="large" onClick={()=>this.handleSubmit()}>确定</Button>

                    <Button type="primary" htmlType="reset" size="large" onClick={()=>this.reset()} style={{ marginLeft: 8 }}>重置</Button>
                </FormItem>

			</Form>

            </SubPage>
        )
    }
}
;
DistributorForm = createForm()(DistributorForm);
export default connect(
    state => {
        let {DistributorList, selectedDistributor} = state;
        return {
            DistributorList, selectedDistributor
        }
    }
)(DistributorForm);