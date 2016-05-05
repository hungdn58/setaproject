<nav class="large-3 medium-4 columns" id="actions-sidebar">
    <ul class="side-nav">
        <li class="heading"><?= __('Actions') ?></li>
        <li><?= $this->Html->link(__('Edit Notification'), ['action' => 'edit', $notification->id]) ?> </li>
        <li><?= $this->Form->postLink(__('Delete Notification'), ['action' => 'delete', $notification->id], ['confirm' => __('Are you sure you want to delete # {0}?', $notification->id)]) ?> </li>
        <li><?= $this->Html->link(__('List Notifications'), ['action' => 'index']) ?> </li>
        <li><?= $this->Html->link(__('New Notification'), ['action' => 'add']) ?> </li>
    </ul>
</nav>
<div class="notifications view large-9 medium-8 columns content">
    <h3><?= h($notification->title) ?></h3>
    <table class="vertical-table">
        <tr>
            <th><?= __('Title') ?></th>
            <td><?= h($notification->title) ?></td>
        </tr>
        <tr>
            <th><?= __('Content') ?></th>
            <td><?= h($notification->content) ?></td>
        </tr>
        <tr>
            <th><?= __('Id') ?></th>
            <td><?= $this->Number->format($notification->id) ?></td>
        </tr>
        <tr>
            <th><?= __('DelFlg') ?></th>
            <td><?= $this->Number->format($notification->delFlg) ?></td>
        </tr>
        <tr>
            <th><?= __('CreateDate') ?></th>
            <td><?= h($notification->createDate) ?></td>
        </tr>
        <tr>
            <th><?= __('UpdateDate') ?></th>
            <td><?= h($notification->updateDate) ?></td>
        </tr>
    </table>
</div>
