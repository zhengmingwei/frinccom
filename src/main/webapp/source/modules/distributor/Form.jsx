import React from "react";

import {Form, Input, Upload, Button, Icon} from "antd";
import {showModalDialog} from "actions/CommonAction";
import {connect} from 'react-redux';
import {saveDistributor,getDistributor} from "actions/DistributorActions";
import SubPage from 'modules/common/SubPage';
import LimitUpload from 'modules/common/LimitUpload';
const FormItem = Form.Item;
const createForm = Form.create;

class DistributorForm extends React.Component {

    constructor(props) {
        super(props);
    }

    componentDidMount() {
        this.props.dispatch(getDistributor(this.props.params.id));
    }

    shouldComponentUpdate(nextProps, nextState) {
        const statusId = nextProps.params.id;
        const oldStatusId = this.props.params.id;

        if (statusId != oldStatusId){
            this.props.dispatch(getDistributor(statusId));
            return false;
        }
        return true;
    }
    reset() {
        this.props.form.resetFields();
        //this.props.dispatch(userCriteriaChanged());
    }


    handleSubmit() {
        E.addOneTimeEventListener("todistributorlist",  (e) => {
            this.props.history.push('/manager/distributor/list');
        })
        this.props.form.validateFields((err, values) => {
            if (!err) {
                let rows = {};
                Object.assign(rows, this.props.form.getFieldsValue());
                this.props.dispatch(saveDistributor(rows))
            } else {
                console.error('validate failed');
            }
        })
    }
    normFile=(e)=> {
        console.log('Upload event:', e);
        return e ;
    }

    render() {
        const {getFieldDecorator} = this.props.form;
        const {selectedDistributor:{id, name="", orgCode="", legalPerson="", contact="", phone="", fax="", mail="",province="",address="",businessLicense=[],orgCodeCertification=[],taxCertificate=[]}} = this.props;
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
                    {getFieldDecorator('name', {
                        validateTrigger: ['onChange', 'onBlur'],
                        rules: [{
                            required: true,
                            whitespace: false,
                            message: "请输入企业名称.",
                        }],
                        initialValue: name,
                        })(

						<Input placeholder="企业名称"/>
                    )}
				</FormItem>
				<FormItem
                    {...formItemLayout}
					label="法人"
				>
                    {getFieldDecorator('legalPerson', {
                        initialValue: legalPerson,
                    })(
						<Input placeholder="法人"/>
                    )}
				</FormItem>
				<FormItem
                    {...formItemLayout}
					label="组织机构代码"
				>
                    {getFieldDecorator('orgCode', {
                        initialValue: orgCode,
                    })(
						<Input placeholder="组织机构代码"/>
                    )}
				</FormItem>
				<FormItem
                    {...formItemLayout}
					label="联系人"
				>
                    {getFieldDecorator('contact', {
                        initialValue: contact,
                    })(
						<Input placeholder="联系人"/>
                    )}
				</FormItem>
				<FormItem
                    {...formItemLayout}
					label="电话"
				>
                    {getFieldDecorator('phone', {
                        initialValue: phone,
                    })(
						<Input placeholder="电话"/>
                    )}
				</FormItem>
				<FormItem
                    {...formItemLayout}
					label="传真"
				>
                    {getFieldDecorator('fax', {
                        initialValue: fax,
                    })(
						<Input placeholder="传真"/>
                    )}
				</FormItem>
                <FormItem
                    {...formItemLayout}
					label="邮箱"
				>
                    {getFieldDecorator('mail', {
                        initialValue: mail,
                    })(
						<Input placeholder="邮箱"/>
                    )}
				</FormItem>
                <FormItem
                    {...formItemLayout}
					label="所在省市"
				>
                    {getFieldDecorator('province', {
                        initialValue: province,
                    })(
						<Input placeholder="所在省市"/>
                    )}
				</FormItem>
                <FormItem
                    {...formItemLayout}
					label="办公地址"
				>
                    {getFieldDecorator('address', {
                        initialValue: address,
                    })(
						<Input placeholder="办公地址"/>
                    )}
				</FormItem>
                <FormItem
                    {...formItemLayout}
                    label="上传营业执照副本"
                >
                    {getFieldDecorator('businessLicense', {
                        valuePropName: 'fileList',
                        getValueFromEvent: this.normFile,
                    })(
                        <LimitUpload name="uploadFile" action="/incc/file/upload/1/1" maxlength="1" initialValue={businessLicense}>
                            <Button>
                                <Icon type="upload" /> 上传
                            </Button>
                        </LimitUpload>

                    )}
                </FormItem>
                <FormItem
                    {...formItemLayout}
                    label="上传组织机构代码"
                >
                    {getFieldDecorator('orgCodeCertification', {
                        valuePropName: 'fileList',
                        getValueFromEvent: this.normFile,
                    })(
                        <LimitUpload name="uploadFile" action="/incc/file/upload/1/1" maxlength="1" initialValue={orgCodeCertification}>
                            <Button>
                                <Icon type="upload" /> 上传
                            </Button>
                        </LimitUpload>

                    )}
                </FormItem>
                <FormItem
                    {...formItemLayout}
                    label="上传税务登记证"
                >
                    {getFieldDecorator('taxCertificate', {
                        valuePropName: 'fileList',
                        getValueFromEvent: this.normFile,
                    })(
                        <LimitUpload name="uploadFile" action="/incc/file/upload/1/1" maxlength="1" initialValue={taxCertificate}>
                            <Button>
                                <Icon type="upload" /> 上传
                            </Button>
                        </LimitUpload>

                    )}
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