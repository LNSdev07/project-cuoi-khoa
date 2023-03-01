import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-quan-ly-khach-hang',
  templateUrl: './quan-ly-khach-hang.component.html',
  styleUrls: ['./quan-ly-khach-hang.component.scss']
})
export class QuanLyKhachHangComponent implements OnInit {
  ngOnInit(): void {
   
  }
  isCollapseFilter = false;
  isEnterpriseTab = true;

  onClickCollapseFilter(event:any) {
    this.isCollapseFilter = event.collapsed;
  }
}
