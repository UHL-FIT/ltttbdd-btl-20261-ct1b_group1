# 🔥 QUY TRÌNH LÀM VIỆC VỚI GIT

Tất cả thành viên tuân thủ 100% các quy tắc dưới đây.

---

## 1. QUY TẮC GIT (GITFLOW)

### A. CÁC NHÁNH CHÍNH

- `main`: Nhánh "sạch", chỉ dùng để demo, **[CẤM PUSH]**.
- `develop`: Nhánh tích hợp code, **[CẤM PUSH]** (Chỉ được merge qua Pull Request).

### B. QUY TRÌNH LÀM 1 TASK

Đây là quy trình khi mọi thứ suôn sẻ:

1. **Lấy code mới nhất:** (Luôn làm đầu tiên!)
   ```
   git checkout develop
   git pull origin develop
   ```
2. **Tạo nhánh mới:** (Đặt tên theo task Trello)
   ```
   git checkout -b feature/crud-category
   ```
3. **Code & Commit:**
   - Code tính năng của bạn. 
   - git add . 
   -`git commit -m "feat: Tao xong API GET category"`
4. **Đẩy code lên Git:**
   ```
   git push origin feature/crud-category
   ```
5. **Tạo Pull Request (PR):**
   - Lên GitHub/GitLab, tạo Pull Request (PR).
   - Từ nhánh: `feature/crud-category` -> Vào nhánh: `develop`.
6. **Cập nhật Trello:** (Xem Thẻ **Quy Trình Trello**).

---

## 2. XỬ LÝ CÁC TÌNH HUỐNG KHI CODE (QUAN TRỌNG!)

### TÌNH HUỐNG A: "Pull Request (PR) bị Lead YÊU CẦU SỬA"

- **Khi nào:** Lead review code của bạn và để lại comment yêu cầu sửa. Thẻ Trello bị kéo trả về `In Progress`.
- **KHÔNG:** Tạo PR mới.
- **LÀM:**
  1. Mở lại nhánh của bạn (ví dụ: `feature/crud-category`).
  2. Tiếp tục code và sửa theo yêu cầu của Lead.
  3. Sau khi sửa xong, chỉ cần `commit` và `push` lên lại:
     ```
     git add .
     git commit -m "fix: Sua code theo review cua lead"
     git push origin feature/crud-category
     ```
  4. PR sẽ tự động được cập nhật.                                                 Comment vào PR: _"@Penguninn (Đại)  Đã sửa xong rồi, check lại nhé."_

### TÌNH HUỐNG B: "Nhánh bị `behind` (tụt hậu) so với `develop`"

- **Khi nào:** Bạn đang code thì M2 (hoặc M5) làm xong 1 task khác và đã được merge vào `develop`. Nhánh của bạn giờ đã cũ hơn `develop`.
- **Việc cần làm:** Bạn phải cập nhật code mới từ `develop` vào nhánh của mình **TRƯỚC KHI** tạo PR.
- **LÀM:**
  1. Commit hết các thay đổi của bạn trên nhánh `feature/...` của bạn đang làm trước đã.
  2. Chuyển về `develop`:
     ```
     git checkout develop
     ```
  3. Lấy code mới nhất:
     ```
     git pull origin develop
     ```
  4. Quay lại nhánh của bạn:
     ```
     git checkout feature/crud-category
     ```
  5. **Trộn** `develop` vào nhánh của bạn:
     ```
     git merge develop
     ```
  6. Đẩy code đã trộn lên lại:
     ```
     git push origin feature/crud-category
     ```
  7. Bây giờ mới đi tạo Pull Request (PR).

### TÌNH HUỐNG C: "Bị CONFLICT (xung đột)!" 😱

- **Khi nào:** Bạn làm **Bước 5** ở **Tình huống B** (tức là khi `git merge develop`), và Git báo lỗi `CONFLICT`.
- **Tại sao:** Vì code mới trên `develop` và code của bạn... **cả hai cùng sửa CHUNG 1 DÒNG**. Git không biết chọn ai.
- **Đừng lo lắng!** Đây là việc bình thường.
- **LÀM:**
  1. Mở project trong VS Code/Intelịi.
  2. VS Code/Intelịi sẽ tô đỏ các file bị conflict. Mở các file đó lên.
  3. Bạn sẽ thấy các dấu `<<<<< HEAD`, `======`, `>>>>> develop`.
  4. Bạn phải quyết định giữ code nào, xóa code nào. VS Code có các nút "Accept Current Change" (Giữ code của bạn), "Accept Incoming Change" (Giữ code của `develop`), "Accept Both Changes" (Giữ cả hai).
  5. **Quan trọng:** Sau khi chọn, **HÃY XÓA HẾT** các dấu `<<<<<`, `======`, `>>>>>` đi.                                                                      Đảm bảo file code chạy được.
  6. Sau khi sửa xong TẤT CẢ các file bị conflict:
     ```
     git add .
     git commit
     ```
     _(Git sẽ tự mở một message commit "Merge branch 'develop'...", bạn chỉ cần save và đóng lại)_
  7. Giờ thì push code đã giải quyết conflict lên:
     ```
     git push origin feature/crud-category
     ```
  8. **NẾU KHÔNG TỰ TIN:** Đừng đoán mò! **HÃY GỌI Lead** và share màn hình để được hỗ trợ.

---🔥 QUY TRÌNH LÀM VIỆC VỚI GIT

Tất cả thành viên tuân thủ 100% các quy tắc dưới đây.

1. QUY TẮC GIT (GITFLOW)

A. CÁC NHÁNH CHÍNH

main: Nhánh "sạch", chỉ dùng để demo, [CẤM PUSH].

develop: Nhánh tích hợp code, [CẤM PUSH] (Chỉ được merge qua Pull Request).

B. QUY TRÌNH LÀM 1 TASK

Đây là quy trình khi mọi thứ suôn sẻ:

Lấy code mới nhất: (Luôn làm đầu tiên!)

git checkout develop
git pull origin develop

Tạo nhánh mới: (Đặt tên theo task Trello)

git checkout -b feature/crud-category

Code & Commit:

Code tính năng của bạn.

Commit nhiều lần với message rõ ràng.

Ví dụ: git commit -m "feat: Tao xong API GET category"

Đẩy code lên Git:

git push origin feature/crud-category

Tạo Pull Request (PR):

Lên GitHub/GitLab, tạo Pull Request (PR).

Từ nhánh: feature/crud-category -> Vào nhánh: develop.

Cập nhật Trello: (Xem Thẻ Quy Trình Trello).

2. XỬ LÝ CÁC TÌNH HUỐNG KHI CODE (QUAN TRỌNG!)

TÌNH HUỐNG A: "Pull Request (PR) bị Lead YÊU CẦU SỬA"

Khi nào: Lead review code của bạn và để lại comment yêu cầu sửa. Thẻ Trello bị kéo trả về In Progress.

KHÔNG: Tạo PR mới.

LÀM:

Mở lại nhánh của bạn (ví dụ: feature/crud-category).

Tiếp tục code và sửa theo yêu cầu của Lead.

Sau khi sửa xong, chỉ cần commit và push lên lại:

git add .
git commit -m "fix: Sua code theo review cua lead"
git push origin feature/crud-category

PR sẽ tự động được cập nhật.                                                 Comment vào PR: @Penguninn (Đại)  Đã sửa xong rồi, check lại nhé."

TÌNH HUỐNG B: "Nhánh bị behind (tụt hậu) so với develop"

Khi nào: Bạn đang code thì M2 (hoặc M5) làm xong 1 task khác và đã được merge vào develop. Nhánh của bạn giờ đã cũ hơn develop.

Việc cần làm: Bạn phải cập nhật code mới từ develop vào nhánh của mình TRƯỚC KHI tạo PR.

LÀM:

Commit hết các thay đổi của bạn trên nhánh feature/... của bạn đang làm trước đã.

Chuyển về develop:

git checkout develop

Lấy code mới nhất:

git pull origin develop

Quay lại nhánh của bạn:

git checkout feature/crud-category

Trộn develop vào nhánh của bạn:

git merge develop

Đẩy code đã trộn lên lại:

git push origin feature/crud-category

Bây giờ mới đi tạo Pull Request (PR).

TÌNH HUỐNG C: "Bị CONFLICT (xung đột)!" 😱

Khi nào: Bạn làm Bước 5 ở Tình huống B (tức là khi git merge develop), và Git báo lỗi CONFLICT.

Tại sao: Vì code mới trên develop và code của bạn... cả hai cùng sửa CHUNG 1 DÒNG. Git không biết chọn ai.

Đừng lo lắng! Đây là việc bình thường.

LÀM:

Mở project trong VS Code/Intelịi.

VS Code/Intelịi sẽ tô đỏ các file bị conflict. Mở các file đó lên.

Bạn sẽ thấy các dấu <<<<< HEAD, ======, >>>>> develop.

Bạn phải quyết định giữ code nào, xóa code nào. VS Code có các nút "Accept Current Change" (Giữ code của bạn), "Accept Incoming Change" (Giữ code của develop), "Accept Both Changes" (Giữ cả hai).

Quan trọng: Sau khi chọn, HÃY XÓA HẾT các dấu <<<<<, ======, >>>>> đi.                                                                      Đảm bảo file code chạy được.

Sau khi sửa xong TẤT CẢ các file bị conflict:

git add .
git commit

(Git sẽ tự mở một message commit "Merge branch 'develop'...", bạn chỉ cần save và đóng lại)
7. Giờ thì push code đã giải quyết conflict lên:

git push origin feature/crud-category

NẾU KHÔNG TỰ TIN: Đừng đoán mò! HÃY GỌI Lead và share màn hình để được hỗ trợ.