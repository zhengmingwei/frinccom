import React from "react";

import {Form, Input, Upload, Button, Icon, Modal, DatePicker} from "antd";
// import {showModalDialog} from "actions/CommonAction";
import {connect} from 'react-redux';
import {addSpecialItem} from "actions/SpecialItemActions";
import ImgUpload from 'modules/common/ImgUpload'
const FormItem = Form.Item;
const createForm = Form.create;


class SpecialItemForm extends React.Component {

    constructor(props) {
        super(props);
    }

    reset() {
        this.props.form.resetFields();
        //this.props.dispatch(userCriteriaChanged());
    }

    __handleOk() {
        this.props.form.validateFields((err, values) => {
            if (!err) {
                let rows = {};
                Object.assign(rows, this.props.form.getFieldsValue());
                // console.log(this.refs.uploadFile);
                console.log(rows);
                Object.assign(rows, this.props.form.getFieldsValue());
                if (rows.file){
                    let fileList = [];
                    rows.file.map((item)=>{fileList.push(item.response.result)});
                    console.log(fileList)
                    console.log(JSON.stringify(fileList))
                    rows.file = JSON.stringify(fileList);
                }
                this.props.dispatch(addSpecialItem(rows))
                this.reset();
                this.props.close();
            } else {
                console.error('validate failed');
            }
        })
    }
    normFile=(e)=> {
        console.log('Upload event:', e);
        if (Array.isArray(e)) {
            return e;
        }
        let result = "";
        if (e.file.response){
            result=  e.file.response.result;
            console.log(JSON.stringify(result) );
            // return JSON.stringify(result);
        }

        // return JSON.stringify(e.file.response) ;
        return e && e.fileList;
    }


    render() {
        const {getFieldDecorator} = this.props.form;
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
            <Modal title={"添加商品特殊行政审批"}
                   visible={this.props.visible}
                   onClose={this.props.close}
                   // onClose={()=>this.__handleClose()}
                   onOk={()=>this.__handleOk()}
                   // onCancel={()=>this.__handleCancel()}
                   onCancel={this.props.close}
                   okText={ "确定"}
                   cancelText={ "取消"}
            >
                <Form>
                    <FormItem
                        {...formItemLayout}
                        label="审批项目"

                    >
                        {getFieldDecorator('name', {})(

                            <Input placeholder="审批项目"/>
                        )}
                    </FormItem>
                    <FormItem
                        {...formItemLayout}
                        label="凭证号码"
                    >
                        {getFieldDecorator('code', {})(
                            <Input placeholder="凭证号码"/>
                        )}
                    </FormItem>
                    <FormItem
                        {...formItemLayout}
                        label="有效时间"
                    >
                        {getFieldDecorator('expiryDate', {})(
                            <DatePicker />
                        )}
                    </FormItem>
                    <FormItem
                        {...formItemLayout}
                        label="审批机构"
                    >
                        {getFieldDecorator('auditOrg', {})(
                            <Input placeholder="审批机构"/>
                        )}
                    </FormItem>
                    <FormItem
                        {...formItemLayout}
                        label="上传证件"
                    >
                        {getFieldDecorator('file', {
                            valuePropName: 'fileList',
                            getValueFromEvent: this.normFile,
                            showUploadList:{ showPreviewIcon: true, showRemoveIcon: true },
                        })(
                            <Upload name="uploadFile" action="/incc/file/upload/1/4">
                                <Button>
                                    <Icon type="upload" /> 上传
                                </Button>
                            </Upload>

                        )}
                    </FormItem>

                </Form>
            </Modal>
        )
    }
}
;
SpecialItemForm = createForm({})(SpecialItemForm);
export default connect(
    state => {
        let {specialItemList} = state;
        return {
            specialItemList
        }
    }
)(SpecialItemForm);