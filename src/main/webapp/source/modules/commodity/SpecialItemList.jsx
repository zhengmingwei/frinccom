import React from 'react';
import {connect} from 'react-redux';
import {Icon, Table, Modal, Row, Col, Button} from 'antd';
// import {refreshSpecialItemList, changeSpecialItemStatus} from 'actions/SpecialItemActions';
import {showModalDialog} from 'actions/CommonAction';
import SpecialItemForm from 'modules/commodity/SpecialItemForm';
import FileList from 'modules/common/FileList';
import {delSpecialItem} from "actions/SpecialItemActions";
const confirm = Modal.confirm;
/**
 * SpecialItem: zyj
 * Date: 16/7/19.
 * Time: 上午10:26.
 */
class SpecialItemList extends React.Component {

    constructor(props) {
        super(props);
        this.state =  {
            visible: false
        }
    }

    show(){
        this.setState({visible: true});
    }

    hidden(){
        this.setState({visible: false});
    }
    add(){
        const form = <SpecialItemForm {...this.props}/>;
        // const dialogConfig = {title:"添加商品特殊行政审批", visible:true, content:form, okHandler:()=>{console.log(form)}};
        // this.props.dispatch(showModalDialog(dialogConfig));
    }

    del(id){
        this.props.dispatch(delSpecialItem(id))
    }

    render() {
        const {editAble} = this.props;
        const {specialItemList=[]} = this.props;
        // const specialItemList = [{"name":"aaaa1","code":"凭证1","expiryDate":"凭证fff","auditOrg":"凭证ffrr","file":'[{"uid":"7051ce422c9f2eabe963163d4b73a276128a991b","url":"http://localhost:8080/incc/file/get/1/4/7051ce422c9f2eabe963163d4b73a276128a991b.jpg","ext":"jpg","name":"1.pic.jpg","status":"done"},{"uid":"2990cda696b888b39a2fb42b5c149a7d67e7b90b","url":"http://localhost:8080/incc/file/get/1/4/2990cda696b888b39a2fb42b5c149a7d67e7b90b.jpg","ext":"jpg","name":"07090518-029ff26fac014d72a7786937e8319c78.jpg","status":"done"}]'},{"name":"aaaa2","code":"凭证2","expiryDate":"凭证fff","auditOrg":"凭证ffrr","file":""}]
        let rows = [];
                {
                    if(specialItemList)specialItemList.map((item, index)=>{
                        const fileInfo = eval(item.file);
                        rows.push(
                            <Row key={index} style={{borderBottom: "1px solid rgb(236, 236, 236)",padding: 5}}>
                            <Col span={20}>
                                <Row><Col span={12}>审批项目:{item.name}</Col><Col span={12}>凭证号码:{item.code}</Col></Row>
                                <Row><Col span={12}>有效时间:{item.expiryDate}</Col><Col span={12}>审批机构:{item.auditOrg}</Col></Row>
                                <Row><Col span={2}>证件:</Col><Col span={22}><FileList fileInfoList={fileInfo}/></Col></Row>
                            </Col>
                            <Col span={4}><Button onClick={()=>this.del(item.id)} style={{display:editAble?"":"none"}}>删除</Button></Col>
                        </Row>)
                    }
                    )
                }
        return (
            <div>
                <Button onClick={()=>this.show()} style={{display:editAble?"":"none"}}>添加商品特殊行政审批</Button>
                {rows}
                <SpecialItemForm visible={this.state.visible} close={()=>this.hidden()}/>
            </div>
        )
    }
};
export default connect(
    state => {
        let {specialItemList} = state;
        return {
            specialItemList
        }
    }
)(SpecialItemList);
