package lims.demo.lims_EAM.Service;

import lims.demo.lims_EAM.Mapper.EAM_Mapper;
import lims.demo.lims_EAM.Model.lims_EAM;
import lims.demo.lims_EAM.Model.lims_EAM_menu;
import lims.demo.lims_EAM.Model.lims_EAM_out_return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class EAM_service {


    @Autowired
    private EAM_Mapper eam_mapper;

    /**
     * 查询所有类目库存
     * @return
     */
    public List<lims_EAM_menu> Query_EAM_menu_inventory()
    {
        List<lims_EAM_menu> map =  new ArrayList<lims_EAM_menu>();
        map = eam_mapper.QueryEAM_inventory();
        for(lims_EAM_menu list:map)
        {
            eam_mapper.Update_EAM_menu_inventory(list.getEAM_menu_id(),list.getInventory());
        }
        map = eam_mapper.QueryEAM_menu();
        return map;
    }

    /**
     * 分页查询所有类目
     * @param pageSize
     * @param pageNow
     * @return
     */
    public List<HashMap<String,Object>> QueryEAM(int pageSize,int pageNow){
        List<HashMap<String,Object>> map = new ArrayList<HashMap<String, Object>>();
        pageNow = (pageNow-1)*pageSize;
        map= eam_mapper.QueryEAM(pageSize,pageNow);
        for(int i =0; i<map.size();i++)
        {
        String status = (String)map.get(i).get("lims_EAM_status");
            if("0".equals(status)||"0"==status)
            {
                map.get(i).replace("lims_EAM_status","良好");
            }
            else
            {
                map.get(i).replace("lims_EAM_status","损坏");
            }
        }

        return map;
    }


    /**
     * 插入设备类目
     * @param lims_eam_menu
     * @return
     */
    public boolean Insert_EAM_menu(lims_EAM_menu lims_eam_menu)
    {
        return eam_mapper.Insert_EAM_menu(lims_eam_menu);
    }

    /**
     * 插入设备
     * @param lims_eam
     * @return
     */
    public boolean Insert_EAM(lims_EAM lims_eam)
    {
        return eam_mapper.Insert_EAM(lims_eam);
    }


    /**
     * 查询设备库存
     * @param EAM_menu_Id
     * @return
     */
    public int QueryInventoryEam(int EAM_menu_Id){return eam_mapper.QueryInventoryEam(EAM_menu_Id);}

    /**
     * 修改设备库存呢
     * @param EAM_menu_Id
     * @param nventory
     * @return
     */
    public boolean updateInventory(int EAM_menu_Id,int nventory){return eam_mapper.Update_EAM_menu_inventory(EAM_menu_Id, nventory);}

    /**
     * 设备借出归还
     * @param lims_eam_out_return
     * @return
     */
    public boolean Inser_EAM_out_return(lims_EAM_out_return lims_eam_out_return)
    {

        return  eam_mapper.insert_EAM_out_return(lims_eam_out_return);
    }

    /**
     * 统计设备总数
     * @return
     */
    public int countEAM(){
        return eam_mapper.CountEam();
    }

    /**
     * 修改设备信息
     * @param name
     * @param status
     * @param id
     * @return
     */
    public boolean updateEam(String name,String status,int id){
        return eam_mapper.updateEam(name, status, id);
    }

    public boolean deleteEam(int id){
        return eam_mapper.deleteEam(id);
    }
}
